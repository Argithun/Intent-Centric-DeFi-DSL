package transaction;

import ast.Node;
import infrastrcuture.Web3jBuilder;
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
import tool.PrivateKeyManager;
import tool.TriggerConditionCheck;

import java.io.IOException;

import static settings.Settings.WAIT_TIME_LIMIT;

public class TransSubmitter {

    public static void submitTransactions(Node root) throws Exception {
        for (Node.TriggerStatement triggerStatement : root.getTriggerStatements()) {
            boolean isSuccess = submitSingleStatements(triggerStatement);
            if (isSuccess) {
                System.out.println("[SUCCESS]\n" + triggerStatement.toString());
            } else {
                System.out.println("[FAIL] Sorry, the statement:\n" +
                        triggerStatement.toString() +
                        "failed! And transactions after it won't be executed.");
                break;
            }
            System.out.println("------------------------------------------------");
        }
    }

    public static boolean submitSingleStatements(Node.TriggerStatement triggerStatement) throws Exception {
        // 交易发生前置条件检查
        long startTime = System.currentTimeMillis();

        while (triggerStatement.isHasTriggerCondition() &&
                !TriggerConditionCheck.checkTriggerCondition(triggerStatement.getTriggerCondition())) {
            if (System.currentTimeMillis() - startTime > WAIT_TIME_LIMIT) {
                System.out.println("Transaction waiting TIMEOUT!\n");
                return false;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }

        System.out.println("[Notice] Trigger condition reached! Start constructing rawTransaction ...");

        if (triggerStatement.getStatement() instanceof Node.AccountStatement) {
            PrivateKeyManager.switchAccount(((Node.AccountStatement) triggerStatement.getStatement()).
                    getPrivateKey().getContent());
            return true;
        }

        // 生成交易
        TransGenerator transGenerator = new TransGenerator(triggerStatement.getStatement());
        transGenerator.genTransaction();
        RawTransaction rawTransaction = transGenerator.getRawTransaction();
        Boolean constructSuccess = transGenerator.getConstructSuccess();

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


                if (transGenerator.getPreRawTransaction() != null) {
                    String transHash = submitSingleTransaction(transGenerator.getPreRawTransaction());
                    if (transHash == null) {
                        System.out.println("[Notice] Stake rawTransaction's approve operation failed.");
                        return false;
                    } else {
                        System.out.println("[Notice] Stake rawTransaction's approve operation succeed.");
                        waitForTransactionConfirmation(transHash);
                        return isTransactionSuccessful(transHash);
                    }
                }

//                if (!simulateTransaction(transGenerator.getTransaction())) {
//                    return false;
//                }

                String transHash = submitSingleTransaction(rawTransaction);
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

    public static boolean simulateTransaction(Transaction transaction) throws IOException {
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

    public static String submitSingleTransaction(RawTransaction rawTransaction) throws Exception {
        Web3j web3j = Web3jBuilder.buildWeb3j();

        if (!PrivateKeyManager.checkPrivateKeyFormat(Settings.ACCOUNT_PRIVATE_KEY)) {
            System.out.println("Please use switch account statement to set your own private key of account.");
            return null;
        }

        Credentials credentials = Credentials.create(Settings.ACCOUNT_PRIVATE_KEY);

        // 对交易进行签名
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String signedTransactionData = Numeric.toHexString(signedMessage);

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

    public static void waitForTransactionConfirmation(String transactionHash) throws Exception {
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
            Thread.sleep(60000);
        }
    }

    public static boolean isTransactionSuccessful(String transactionHash) {
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
