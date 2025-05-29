package tool;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import infrastrcuture.Web3jBuilder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import status_predictor.context_constructor.ContextConstructor;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

public class PredictConditionCheck {

    public static boolean checkPredictCondition(String targetHash,
                                                String curTransactionPath, String predictedTransactionPath,
                                                String futureContextPath)
            throws IOException, InterruptedException {

        // 设置工作目录：status_predictor 文件夹
        File workDir = new File("status_predictor");

        // step 1: 调用 predictor.ts
        ProcessBuilder pb1 = new ProcessBuilder(
                "C:\\Program Files\\nodejs\\npx.cmd", "tsx",
                "next_block_predictor/predictor.ts",
                "../" + curTransactionPath.replace("\\", "/"),
                "../" + predictedTransactionPath.replace("\\", "/")
        );
        pb1.directory(workDir);
        pb1.inheritIO();
        int exit1 = pb1.start().waitFor();
        if (exit1 != 0) {
            System.err.println("Next block predictor failed.");
            return true;
        }

        // step 2: 调用 constructor
        ContextConstructor.contextConstructor(targetHash,
                "./status_predictor/" + predictedTransactionPath,
                "./status_predictor/" + futureContextPath);

        return loadPredictedTransactionsAndIfFail(curTransactionPath,
                predictedTransactionPath, futureContextPath);
    }

    private static boolean loadPredictedTransactionsAndIfFail(String curTransactionPath,
                                                              String predictedTransactionPath, String futureContextPath) throws IOException {
        HashMap<String, Transaction> hashToTransaction = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        // 读取当前交易
        JsonNode curTx = mapper.readTree(
                new File("./status_predictor/" + curTransactionPath));
        String curHash = curTx.get("hash").asText();
        Transaction curRawTx = Transaction.createFunctionCallTransaction(
                curTx.get("from").asText(),
                new BigInteger(curTx.get("nonce").asText()),
                new BigInteger(curTx.get("gasPrice").asText()),
                new BigInteger(curTx.get("gas").asText()),
                curTx.get("to").asText(),
                new BigInteger(curTx.get("value").asText()),
                curTx.get("data").asText()
        );
        hashToTransaction.put(curHash, curRawTx);

        // 读取预测交易
        JsonNode predictedTxs = mapper.readTree(
                new File("./status_predictor/" + predictedTransactionPath));
        for (JsonNode tx : predictedTxs) {
            if (tx.get("hash") == null || tx.get("from") == null || tx.get("nonce") == null ||
                    tx.get("gasPrice") == null || tx.get("gas") == null ||
                    tx.get("to") == null || tx.get("value") == null || tx.get("input") == null) {
                continue;
            }

            String hash = tx.get("hash").asText();

            Transaction nowTx = Transaction.createFunctionCallTransaction(
                    tx.get("from").asText(),
                    new BigInteger(tx.get("nonce").asText().substring(2), 16),
                    new BigInteger(tx.get("gasPrice").asText().substring(2), 16),
                    new BigInteger(tx.get("gas").asText().substring(2), 16),
                    tx.get("to").asText(),
                    new BigInteger(tx.get("value").asText().substring(2), 16),
                    tx.get("input").asText()
            );
            hashToTransaction.put(hash, nowTx);
        }

        return predictIfMayFail(curHash, hashToTransaction, futureContextPath);
    }

    private static boolean predictIfMayFail(
            String curHash, HashMap<String, Transaction> transactionHashMap,
            String futureContextPath) throws IOException {
        long startTime = System.currentTimeMillis();  // 记录开始时间

        Web3j web3j = Web3jBuilder.buildWeb3j();
        int successSituationNum = 0;
        int failSituationNum = 0;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode futureContexts = mapper.readTree(
                new File("./status_predictor/" + futureContextPath));

        for (JsonNode context : futureContexts) {
            JsonNode txSequence = context.get("txSequence");
            for (JsonNode hashNode : txSequence) {
                String hash = hashNode.asText();
                Transaction tx = transactionHashMap.get(hash);
                if (tx == null) {
                    continue;
                }

                boolean success = simulateTransaction(web3j, tx);

                if (hash.equals(curHash)) {
                    if (success) {
                        successSituationNum++;
                    } else {
                        failSituationNum++;
                    }
                    break;
                }
            }
        }

        long endTime = System.currentTimeMillis();  // 记录结束时间
        System.out.println("Simulation time: " + (endTime - startTime) + " ms");
        return successSituationNum >= failSituationNum * 2;
    }

    private static boolean simulateTransaction(Web3j web3j, Transaction transaction) {
        try {
            EthCall response = web3j.ethCall(
                    transaction, DefaultBlockParameterName.LATEST
            ).send();

            return !response.isReverted();
        } catch (Exception e) {
            return false;
        }
    }

}
