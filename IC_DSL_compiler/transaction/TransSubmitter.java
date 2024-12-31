package transaction;

import ast.Node;
import infrastrcuture.QueryService;
import infrastrcuture.Web3jBuilder;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Numeric;
import settings.Settings;
import tool.AfterConditionCheck;
import tool.TriggerConditionCheck;

import java.math.BigInteger;

import static settings.Settings.DEFAULT_GAS_LIMIT;
import static settings.Settings.WAIT_TIME_LIMIT;

public class TransSubmitter {

    public static void submitTransactions(Node root) throws Exception {
        for (Node.TriggerStatement triggerStatement : root.getTriggerStatements()) {
            boolean isSuccess = submitSingleStatements(triggerStatement);
            if (isSuccess) {
                System.out.println("[SUCCESS]\n" + triggerStatement.toString());
            } else {
                System.out.println("[FAIL] Sorry, the transaction:\n" +
                        triggerStatement.toString() +
                        "failed! And transactions after it won't be executed.");
                break;
            }
            System.out.println("------------------------------------------------");
        }
    }

    private static boolean submitSingleStatements(Node.TriggerStatement triggerStatement) throws Exception {
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

        System.out.println("[Notice] Trigger condition reached! Start constructing transaction ...");


        // 生成交易
        TransGenerator transGenerator = new TransGenerator(triggerStatement.getStatement());
        transGenerator.genTransaction();
        Transaction transaction = transGenerator.getTransaction();
        Boolean constructSuccess = transGenerator.getConstructSuccess();

        if (constructSuccess) {
            System.out.println("[Notice] Constructing transaction succeed.");
            if (transaction != null) {
                // 交易是否满足条件检查
                AfterConditionCheck afterConditionCheck = new AfterConditionCheck(triggerStatement.getStatement(),
                        transaction, transGenerator.getRouterAddress());
                if (!afterConditionCheck.checkAfterCondition(triggerStatement.getCheckCondition())) {
                    return false;
                }

                System.out.println("[Notice] Transaction condition reached! Start submitting transaction ...");


                if (transGenerator.getPreTransaction() != null) {
                    boolean preSucceed = submitSingleTransaction(transGenerator.getPreTransaction(), afterConditionCheck);
                    if (!preSucceed) {
                        System.out.println("[Notice] Stake transaction's approve operation failed.");
                        return false;
                    }
                }

                return submitSingleTransaction(transaction, afterConditionCheck);
            } else {    // NFT
                System.out.println("[Notice] NFT transaction will be constructed and submitted as an order or listing. Please keep tracing the transaction.");
                return true;
            }
        } else {
            System.out.println("[Notice] Constructing transaction failed.");
            return false;
        }
    }

    private static boolean submitSingleTransaction(Transaction transaction, AfterConditionCheck afterConditionCheck) throws Exception {
        Web3j web3j = Web3jBuilder.buildWeb3j();
        Credentials credentials = Credentials.create(Settings.ACCOUNT_PRIVATE_KEY);
        String fromAddress = transaction.getFrom();
        BigInteger nonce = QueryService.getNonce(fromAddress);

        BigInteger gasLimit = DEFAULT_GAS_LIMIT;
        if (afterConditionCheck.getGasLimit() != null) {
            gasLimit = afterConditionCheck.getGasLimit();
        }

        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce,
                new BigInteger(transaction.getGasPrice().substring(2), 16),
                gasLimit,
                transaction.getTo(),
                transaction.getValue() == null ? BigInteger.ZERO : new BigInteger(transaction.getValue()),
                transaction.getData()
        );

        // 对交易进行签名
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String signedTransactionData = Numeric.toHexString(signedMessage);

        // 提交交易到区块链
        EthSendTransaction response = web3j.ethSendRawTransaction(signedTransactionData).send();

        // 检查提交结果
        if (response.hasError()) {
            System.out.println("Transaction failed: " + response.getError().getMessage());
            return false;
        } else {
            System.out.println("Transaction Hash: " + response.getTransactionHash());
        }

        return true;
    }


}
