package transaction;

import ast.Node;
import com.fasterxml.jackson.databind.ObjectMapper;
import infrastrcuture.Web3jBuilder;
import optimize.dependency.DependencyGraph;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Numeric;
import settings.Settings;
import tool.AfterConditionCheck;
import tool.PredictConditionCheck;
import tool.PrivateKeyManager;
import tool.TriggerConditionCheck;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static settings.Settings.WAIT_TIME_LIMIT;

public class TransSubmitter {

    private static Set<Node.TriggerStatement> notExecuted = new HashSet<>();

    public static void submitTransactions(Node root, DependencyGraph dependencyGraph) throws Exception {
        notExecuted = new HashSet<>();

        for (Node.TriggerStatement triggerStatement : root.getTriggerStatements()) {
            if (notExecuted.contains(triggerStatement)) {
                System.out.println("[SKIP]\n" + triggerStatement.toString() +
                        "due to transaction which it depends on failed.");
                continue;
            }

            boolean isSuccess = submitSingleStatement(triggerStatement);
            if (isSuccess) {
                System.out.println("[SUCCESS]\n" + triggerStatement.toString());
            } else {
                System.out.println("[FAIL] Sorry, the statement:\n" +
                        triggerStatement.toString() +
                        "failed! And transactions which depend on it won't be executed.");

                DependencyGraph.GraphNode graphNode = dependencyGraph.getTriggerStatementToNode().get(triggerStatement);
                gatherNotExecuted(graphNode);
            }
            System.out.println("------------------------------------------------");
        }
    }

    private static void gatherNotExecuted(DependencyGraph.GraphNode graphNode) {
        notExecuted.add(graphNode.getSelf());
        for (DependencyGraph.GraphNode son : graphNode.getDominateOver()) {
            gatherNotExecuted(son);
        }
    }

    private static boolean submitSingleStatement(Node.TriggerStatement triggerStatement) throws Exception {
        if (triggerStatement.getStatement() instanceof Node.AccountStatement) {
            return true;
        }

        long startTime = System.currentTimeMillis();
        String privateKey = PrivateKeyManager.statementToPrivateKey.get(triggerStatement);

        while (triggerStatement.isHasTriggerCondition() &&
                !TriggerConditionCheck.checkTriggerCondition(triggerStatement.getTriggerCondition())) {
            if (System.currentTimeMillis() - startTime > WAIT_TIME_LIMIT) {
                System.out.println("Transaction waiting TIMEOUT!\n");
                return false;
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        System.out.println("[Notice] Trigger condition reached! Start constructing rawTransaction ...");

        // 生成交易
        TransGenerator transGenerator = new TransGenerator(triggerStatement.getStatement(), privateKey);
        transGenerator.genTransaction();
        RawTransaction rawTransaction = transGenerator.getRawTransaction();
        boolean constructSuccess = transGenerator.getConstructSuccess();

        if (constructSuccess) {
            System.out.println("[Notice] Constructing rawTransaction succeed.");
            if (rawTransaction != null) {
                // 交易是否满足条件检查
                AfterConditionCheck afterConditionCheck = new AfterConditionCheck(triggerStatement.getStatement(),
                        transGenerator.getTransaction(), transGenerator.getRouterAddress());
                if (!afterConditionCheck.checkAfterCondition(triggerStatement.getCheckCondition())) {
                    return false;
                }

                System.out.println("[Notice] Transaction condition reached! Start submitting rawTransaction ...");


                if (transGenerator.getPreRawTransaction_1() != null) {
                    String transHash = submitSingleTransaction(transGenerator.getPreRawTransaction_1(), privateKey);
                    if (transHash == null) {
                        System.out.println("[Notice] Token approve operation failed.");
                        return false;
                    } else {
                        System.out.println("[Notice] Token approve operation succeed.");
                        waitForTransactionConfirmation(transHash);
                        if (!isTransactionSuccessful(transHash)) {
                            return false;
                        }
                    }
                }

                if (transGenerator.getPreRawTransaction_2() != null) {
                    String transHash = submitSingleTransaction(transGenerator.getPreRawTransaction_2(), privateKey);
                    if (transHash == null) {
                        System.out.println("[Notice] Token approve operation failed.");
                        return false;
                    } else {
                        System.out.println("[Notice] Token approve operation succeed.");
                        waitForTransactionConfirmation(transHash);
                        if (!isTransactionSuccessful(transHash)) {
                            return false;
                        }
                    }
                }

                String transHash = submitSingleTransaction(rawTransaction, privateKey);
                if (transHash == null) {
                    return false;
                } else {
                    waitForTransactionConfirmation(transHash);
                    return isTransactionSuccessful(transHash);
                }

            } else {    // NFT
                System.out.println("[Notice] NFT purchase or sale rawTransaction will be constructed and submitted as an order or listing. Please keep tracing the rawTransaction.");
                return true;
            }
        } else {
            System.out.println("[Notice] Constructing rawTransaction failed.");
            return false;
        }
    }

    private static boolean simulateTransaction(Transaction transaction) throws IOException {
        Web3j web3j = Web3jBuilder.buildWeb3j();
        EthCall response = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();

        if (response.hasError()) {
            System.out.println("Transaction simulation failed: " + response.getError().getMessage());
            System.out.println("Error data: " + response.getError().getData());
            return false;
        } else {
            System.out.println("Transaction simulation succeed. Result: " + response.getValue());
        }
        return true;
    }

    private static String submitSingleTransaction(RawTransaction rawTransaction, String privateKey) throws Exception {
        Web3j web3j = Web3jBuilder.buildWeb3j();

        Credentials credentials = Credentials.create(privateKey);
        // 对交易进行签名
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String signedTransactionData = Numeric.toHexString(signedMessage);

        // 预执行预测交易是否存在失败风险
        if (Settings.IF_PRE_EXECUTION) {
            HashMap<String, Object> txJson = new HashMap<>();
            String from = credentials.getAddress().toLowerCase();
            String to = rawTransaction.getTo().toLowerCase();
            String hash = Integer.toString(rawTransaction.hashCode());
            txJson.put("from", from);
            txJson.put("to", to);
            txJson.put("nonce", rawTransaction.getNonce().toString());
            txJson.put("gas", rawTransaction.getGasLimit().toString());
            txJson.put("gasPrice", rawTransaction.getGasPrice().toString());
            txJson.put("value", rawTransaction.getValue().toString());
            txJson.put("data", rawTransaction.getData());
            txJson.put("hash", hash); // 模拟 transaction hash
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("./status_predictor/predict_input.json"), txJson);
            if (!PredictConditionCheck.checkPredictCondition(hash, "predict_input.json",
                    "predicted_tx_set.json", "future_contexts.json")) {
                System.out.println("[Warning] Please notice: Pre-execution shows current transaction MAY fail...");
            }
        }

        // 提交交易到区块链
        EthSendTransaction response = web3j.ethSendRawTransaction(signedTransactionData).send();

        // 检查提交结果
        if (response.hasError()) {
            System.out.println("Transaction failed: " + response.getError().getMessage());
            return null;
        } else {
            System.out.println("Transaction Hash: " + response.getTransactionHash());
        }

        return response.getTransactionHash();
    }

    private static void waitForTransactionConfirmation(String transactionHash) throws Exception {
        Web3j web3j = Web3jBuilder.buildWeb3j();
        while (true) {
            EthGetTransactionReceipt receipt = web3j.ethGetTransactionReceipt(transactionHash).send();
            if (receipt.getTransactionReceipt().isPresent()) {
                TransactionReceipt transactionReceipt = receipt.getTransactionReceipt().get();
                if (transactionReceipt.getBlockNumber() != null) {
                    System.out.println("Transaction confirmed in block: " + transactionReceipt.getBlockNumber());
                    break;
                }
            } else {
                System.out.println("Transaction still pending... checking again.");
            }
            Thread.sleep(30000);
        }
    }

    private static boolean isTransactionSuccessful(String transactionHash) {
        try {
            Web3j web3j = Web3jBuilder.buildWeb3j();
            EthGetTransactionReceipt transactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).send();

            if (transactionReceipt.getTransactionReceipt().isPresent()) {
                TransactionReceipt receipt = transactionReceipt.getTransactionReceipt().get();
                return "0x1".equals(receipt.getStatus());
            } else {
                System.out.println("Transaction receipt not found with given hash: " + transactionHash);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
