package transaction;

import ast.Node;
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
import tool.AfterConditionCheck;
import tool.PrivateKeyManager;
import tool.TriggerConditionCheck;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

import static settings.Settings.WAIT_TIME_LIMIT;

public class TransSubmitterParallel {
    private static Set<Node.TriggerStatement> notExecuted = ConcurrentHashMap.newKeySet();

    public static void submitTransactionsParallel(DependencyGraph dependencyGraph) {
        notExecuted = ConcurrentHashMap.newKeySet();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Boolean>> futures = new ArrayList<>();
        Map<DependencyGraph.GraphNode, CountDownLatch> nodeLatches = new HashMap<>();

        // 初始化 CountDownLatch，每个节点的依赖节点数作为初始计数
        for (DependencyGraph.GraphNode node : dependencyGraph.getAllNodes()) {
            nodeLatches.put(node, new CountDownLatch(node.getDependOn().size()));
        }

        // 提交根节点（这些节点没有依赖，直接可以执行）
        for (DependencyGraph.GraphNode root : dependencyGraph.getAllNodes()) {
            if (root.getDependOn().size() == 0) {
                futures.add(executorService.submit(() -> submitNode(dependencyGraph, root, nodeLatches, executorService)));
            }
        }

        // 等待所有交易完成
        for (Future<Boolean> future : futures) {
            try {
                if (!future.get()) {
                    System.out.println("[Notice] Some transactions failed or skipped, please check executing log.");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }

    private static void gatherNotExecuted(DependencyGraph.GraphNode graphNode) {
        notExecuted.add(graphNode.getSelf());
        for (DependencyGraph.GraphNode son : graphNode.getDominateOver()) {
            gatherNotExecuted(son);
        }
    }

    private static boolean submitNode(DependencyGraph dependencyGraph, DependencyGraph.GraphNode node,
                                      Map<DependencyGraph.GraphNode, CountDownLatch> nodeLatches, ExecutorService executorService) {
        // 等待所有依赖节点完成
        try {
            CountDownLatch latch = nodeLatches.get(node);
            latch.await(); // 等待所有依赖项执行完成

            if (notExecuted.contains(node.getSelf())) {
                System.out.println("[SKIP]\n" + node.getSelf().toString() +
                        "due to transaction which it depends on failed.");
                // 通知所有受支配节点（dominatedNodes）
                for (DependencyGraph.GraphNode dominatedNode : node.getDominateOver()) {
                    CountDownLatch childLatch = nodeLatches.get(dominatedNode);
                    childLatch.countDown();
                    if (childLatch.getCount() == 0) {
                        executorService.submit(() -> submitNode(dependencyGraph, dominatedNode, nodeLatches, executorService));
                    }
                }
                return false;
            }

            // 执行交易
            boolean success = submitSingleStatement(node.getSelf());
            if (success) {
                System.out.println("[SUCCESS]\n" + node.getSelf().toString());
            } else {
                System.out.println("[FAIL] Sorry, the statement:\n" +
                        node.getSelf().toString() +
                        "failed! And transactions which depend on it won't be executed.");
                gatherNotExecuted(node);
            }

            // 通知所有受支配节点（dominatedNodes）
            for (DependencyGraph.GraphNode dominatedNode : node.getDominateOver()) {
                CountDownLatch childLatch = nodeLatches.get(dominatedNode);
                childLatch.countDown();
                if (childLatch.getCount() == 0) {
                    executorService.submit(() -> submitNode(dependencyGraph, dominatedNode, nodeLatches, executorService));
                }
            }

            return success;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[FAIL] Sorry, the statement:\n" +
                    node.getSelf().toString() +
                    "failed! And transactions which depend on it won't be executed.");
            gatherNotExecuted(node);
            for (DependencyGraph.GraphNode dominatedNode : node.getDominateOver()) {
                CountDownLatch childLatch = nodeLatches.get(dominatedNode);
                childLatch.countDown();
                if (childLatch.getCount() == 0) {
                    executorService.submit(() -> submitNode(dependencyGraph, dominatedNode, nodeLatches, executorService));
                }
            }
            return false;
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
