package optimize.dependency;

import ast.Node;
import ast.Type;
import ast.Word;
import infrastrcuture.ContractFuncService;
import infrastrcuture.QueryService;
import infrastrcuture.Token;
import infrastrcuture.Web3jBuilder;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.web3j.abi.datatypes.primitive.Int;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import tool.Calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class DependencyAnalysis {

    public static DependencyGraph genDependencyGraph(Node rootNode) throws Exception {
        DependencyGraph dependencyGraph = genDependencyGraphPrimarily(rootNode);
        DependencyGraph optimizedGraph = optimizeDependencyGraph(dependencyGraph);
        DependencyGraph repairedGraph = repairPossibleDependency(optimizedGraph);
        graphVisualize(repairedGraph);
        return repairedGraph;
    }

    private static DependencyGraph genDependencyGraphPrimarily(Node rootNode) throws Exception {
        ArrayList<DependencyGraph.GraphNode> roots = new ArrayList<>();
        ArrayList<DependencyGraph.GraphNode> allNodes = new ArrayList<>();

        HashMap<String, ArrayList<DependencyGraph.GraphNode>> walletAssetToIncrease = new HashMap<>();
        HashMap<String, ArrayList<DependencyGraph.GraphNode>> walletAssetToDecrease = new HashMap<>();

        for (Node.TriggerStatement triggerStatement : rootNode.getTriggerStatements()) {
            Node.Statement statement = triggerStatement.getStatement();
            if (statement instanceof Node.AccountStatement) {
                continue;
            } else if (statement instanceof Node.TransferStatement) {
                Node.TransferStatement transferStatement = (Node.TransferStatement) statement;
                String fromWallet = transferStatement.getFromWallet().getKey().getContent();
                String toWallet = transferStatement.getToWallet().getKey().getContent();
                String asset = transferStatement.getAmount().getAsset().getContent();
                String tokenAddress = Token.getContractAddressByToken(transferStatement.getAmount().getAsset());

                ArrayList<DependencyGraph.GraphNode> dependOn = walletAssetToIncrease.get(fromWallet + asset);
                if (dependOn == null) {
                    dependOn = new ArrayList<>();
                }
                DependencyGraph.GraphNode graphNode = new DependencyGraph.GraphNode(
                        triggerStatement, dependOn, new ArrayList<>());

                for (DependencyGraph.GraphNode father : dependOn) {
                    father.getDominateOver().add(graphNode);
                }
                if (dependOn.size() == 0) {
                    roots.add(graphNode);
                }
                BigInteger amount = Calculator.calBinaryExp(
                        transferStatement.getAmount().getBinaryExpression()).multiply(
                        new BigDecimal(Token.getTokenDecimals(tokenAddress))).toBigInteger();
                graphNode.getAssetDecrease().put(fromWallet + asset, amount);
                graphNode.getAssetIncrease().put(toWallet + asset, amount);

                walletAssetToDecrease.computeIfAbsent(fromWallet + asset, k -> new ArrayList<>());
                walletAssetToDecrease.get(fromWallet + asset).add(graphNode);
                walletAssetToIncrease.computeIfAbsent(toWallet + asset, k -> new ArrayList<>());
                walletAssetToIncrease.get(toWallet + asset).add(graphNode);
                allNodes.add(graphNode);

            } else if (statement instanceof Node.BorrowStatement) {
                Node.BorrowStatement borrowStatement = (Node.BorrowStatement) statement;
                String forWallet = borrowStatement.getForWallet().getKey().getContent();
                String asset = borrowStatement.getBorrowAmount().getAsset().getContent();
                String tokenAddress = Token.getContractAddressByToken(borrowStatement.getBorrowAmount().getAsset());

                DependencyGraph.GraphNode graphNode = new DependencyGraph.GraphNode(triggerStatement,
                        new ArrayList<>(), new ArrayList<>());
                roots.add(graphNode);

                BigInteger amount = Calculator.calBinaryExp(
                        borrowStatement.getBorrowAmount().getBinaryExpression()).multiply(
                        new BigDecimal(Token.getTokenDecimals(tokenAddress))).toBigInteger();
                graphNode.getAssetIncrease().put(forWallet + asset, amount);

                walletAssetToIncrease.computeIfAbsent(forWallet + asset, k -> new ArrayList<>());
                walletAssetToIncrease.get(forWallet + asset).add(graphNode);
                allNodes.add(graphNode);

            } else if (statement instanceof Node.RepayBorrowStatement) {
                Node.RepayBorrowStatement repayBorrowStatement = (Node.RepayBorrowStatement) statement;
                String repayWallet = repayBorrowStatement.getWallet().getKey().getContent();
                String asset = repayBorrowStatement.getAmount().getAsset().getContent();
                String tokenAddress = Token.getContractAddressByToken(repayBorrowStatement.getAmount().getAsset());

                ArrayList<DependencyGraph.GraphNode> dependOn = walletAssetToIncrease.get(repayWallet + asset);
                if (dependOn == null) {
                    dependOn = new ArrayList<>();
                }
                DependencyGraph.GraphNode graphNode = new DependencyGraph.GraphNode(
                        triggerStatement, dependOn, new ArrayList<>());
                BigInteger amount = Calculator.calBinaryExp(
                        repayBorrowStatement.getAmount().getBinaryExpression()).multiply(
                        new BigDecimal(Token.getTokenDecimals(tokenAddress))).toBigInteger();
                graphNode.getAssetDecrease().put(repayWallet + asset, amount);

                for (DependencyGraph.GraphNode father : dependOn) {
                    father.getDominateOver().add(graphNode);
                }
                if (dependOn.size() == 0) {
                    roots.add(graphNode);
                }
                walletAssetToDecrease.computeIfAbsent(repayWallet + asset, k -> new ArrayList<>());
                walletAssetToDecrease.get(repayWallet + asset).add(graphNode);
                allNodes.add(graphNode);

            } else if (statement instanceof Node.SwapStatement) {
                Node.SwapStatement swapStatement = (Node.SwapStatement) statement;
                String wallet = swapStatement.getWallet().getKey().getContent();
                Word from = swapStatement.getAmount().getAsset();
                Word to = swapStatement.getAsset();
                String fromAsset = from.getContent();
                String toAsset = to.getContent();
                String fromTokenAddress = Token.getContractAddressByToken(from);
                String toTokenAddress = Token.getContractAddressByToken(to);

                ArrayList<DependencyGraph.GraphNode> dependOn = walletAssetToIncrease.get(wallet + fromAsset);
                if (dependOn == null) {
                    dependOn = new ArrayList<>();
                }
                DependencyGraph.GraphNode graphNode = new DependencyGraph.GraphNode(
                        triggerStatement, dependOn, new ArrayList<>());
                BigDecimal fromCount = Calculator.calBinaryExp(swapStatement.getAmount().getBinaryExpression());
                BigInteger fromAmount = fromCount.multiply(new BigDecimal(Token.getTokenDecimals(fromTokenAddress))).toBigInteger();
                BigInteger toAmount = fromCount.multiply(new BigDecimal(Token.calculateExchangeRate(to, from))).multiply(
                        new BigDecimal(Token.getTokenDecimals(toTokenAddress))).toBigInteger();
                graphNode.getAssetIncrease().put(wallet + toAsset, toAmount);
                graphNode.getAssetDecrease().put(wallet + fromAsset, fromAmount);

                for (DependencyGraph.GraphNode father : dependOn) {
                    father.getDominateOver().add(graphNode);
                }
                if (dependOn.size() == 0) {
                    roots.add(graphNode);
                }
                walletAssetToDecrease.computeIfAbsent(wallet + fromAsset, k -> new ArrayList<>());
                walletAssetToDecrease.get(wallet + fromAsset).add(graphNode);
                walletAssetToIncrease.computeIfAbsent(wallet + toAsset, k -> new ArrayList<>());
                walletAssetToIncrease.get(wallet + toAsset).add(graphNode);
                allNodes.add(graphNode);

            } else if (statement instanceof Node.AddLiquidityStatement) {
                Node.AddLiquidityStatement addLiquidityStatement = (Node.AddLiquidityStatement) statement;
                String wallet = addLiquidityStatement.getWallets().get(0).getKey().getContent();
                String asset1 = addLiquidityStatement.getAmounts().get(0).getAsset().getContent();
                String asset2 = addLiquidityStatement.getAmounts().get(1).getAsset().getContent();
                String tokenAddress1 = Token.getContractAddressByToken(addLiquidityStatement.getAmounts().get(0).getAsset());
                String tokenAddress2 = Token.getContractAddressByToken(addLiquidityStatement.getAmounts().get(1).getAsset());

                ArrayList<DependencyGraph.GraphNode> dependOn1 = walletAssetToIncrease.get(wallet + asset1);
                ArrayList<DependencyGraph.GraphNode> dependOn2 = walletAssetToIncrease.get(wallet + asset2);
                dependOn1 = dependOn1 == null ? new ArrayList<>() : dependOn1;
                dependOn2 = dependOn2 == null ? new ArrayList<>() : dependOn2;

                ArrayList<DependencyGraph.GraphNode> dependOn = new ArrayList<>();
                dependOn.addAll(dependOn1);
                dependOn.addAll(dependOn2);

                DependencyGraph.GraphNode graphNode = new DependencyGraph.GraphNode(
                        triggerStatement, dependOn, new ArrayList<>());
                BigInteger amount1 = Calculator.calBinaryExp(
                        addLiquidityStatement.getAmounts().get(0).getBinaryExpression()).multiply(
                        new BigDecimal(Token.getTokenDecimals(tokenAddress1))).toBigInteger();
                BigInteger amount2 = Calculator.calBinaryExp(
                        addLiquidityStatement.getAmounts().get(1).getBinaryExpression()).multiply(
                        new BigDecimal(Token.getTokenDecimals(tokenAddress2))).toBigInteger();
                graphNode.getAssetDecrease().put(wallet + asset1, amount1);
                graphNode.getAssetDecrease().put(wallet + asset2, amount2);

                for (DependencyGraph.GraphNode father : dependOn) {
                    father.getDominateOver().add(graphNode);
                }
                if (dependOn.size() == 0) {
                    roots.add(graphNode);
                }
                walletAssetToDecrease.computeIfAbsent(wallet + asset1, k -> new ArrayList<>());
                walletAssetToDecrease.get(wallet + asset1).add(graphNode);
                walletAssetToDecrease.computeIfAbsent(wallet + asset2, k -> new ArrayList<>());
                walletAssetToDecrease.get(wallet + asset2).add(graphNode);
                allNodes.add(graphNode);

            } else if (statement instanceof Node.RemoveLiquidityStatement) {
                Node.RemoveLiquidityStatement removeLiquidityStatement = (Node.RemoveLiquidityStatement) statement;
                String wallet = removeLiquidityStatement.getWallets().get(0).getKey().getContent();
                String asset1 = removeLiquidityStatement.getAmounts().get(0).getAsset().getContent();
                String asset2 = removeLiquidityStatement.getAmounts().get(1).getAsset().getContent();
                String tokenAddress1 = Token.getContractAddressByToken(removeLiquidityStatement.getAmounts().get(0).getAsset());
                String tokenAddress2 = Token.getContractAddressByToken(removeLiquidityStatement.getAmounts().get(1).getAsset());

                DependencyGraph.GraphNode graphNode = new DependencyGraph.GraphNode(triggerStatement,
                        new ArrayList<>(), new ArrayList<>());
                BigInteger amount1 = Calculator.calBinaryExp(
                        removeLiquidityStatement.getAmounts().get(0).getBinaryExpression()).multiply(
                        new BigDecimal(Token.getTokenDecimals(tokenAddress1))).toBigInteger();
                BigInteger amount2 = Calculator.calBinaryExp(
                        removeLiquidityStatement.getAmounts().get(1).getBinaryExpression()).multiply(
                        new BigDecimal(Token.getTokenDecimals(tokenAddress2))).toBigInteger();
                graphNode.getAssetIncrease().put(wallet + asset1, amount1);
                graphNode.getAssetIncrease().put(wallet + asset2, amount2);

                roots.add(graphNode);
                walletAssetToIncrease.computeIfAbsent(wallet + asset1, k -> new ArrayList<>());
                walletAssetToIncrease.get(wallet + asset1).add(graphNode);
                walletAssetToIncrease.computeIfAbsent(wallet + asset2, k -> new ArrayList<>());
                walletAssetToIncrease.get(wallet + asset2).add(graphNode);
                allNodes.add(graphNode);

            } else if (statement instanceof Node.StakeStatement) {
                Node.StakeStatement stakeStatement = (Node.StakeStatement) statement;
                String wallet = stakeStatement.getWallet().getKey().getContent();
                String asset = stakeStatement.getAmount().getAsset().getContent();
                String tokenAddress = Token.getContractAddressByToken(stakeStatement.getAmount().getAsset());

                ArrayList<DependencyGraph.GraphNode> dependOn = walletAssetToIncrease.get(wallet + asset);
                if (dependOn == null) {
                    dependOn = new ArrayList<>();
                }
                DependencyGraph.GraphNode graphNode = new DependencyGraph.GraphNode(
                        triggerStatement, dependOn, new ArrayList<>());
                BigInteger amount = Calculator.calBinaryExp(
                        stakeStatement.getAmount().getBinaryExpression()).multiply(
                        new BigDecimal(Token.getTokenDecimals(tokenAddress))).toBigInteger();
                graphNode.getAssetDecrease().put(wallet + asset, amount);

                for (DependencyGraph.GraphNode father : dependOn) {
                    father.getDominateOver().add(graphNode);
                }
                if (dependOn.size() == 0) {
                    roots.add(graphNode);
                }
                walletAssetToDecrease.computeIfAbsent(wallet + asset, k -> new ArrayList<>());
                walletAssetToDecrease.get(wallet + asset).add(graphNode);
                allNodes.add(graphNode);

            } else if (statement instanceof Node.BuyNFTStatement) {
                Node.BuyNFTStatement buyNFTStatement = (Node.BuyNFTStatement) statement;
                String wallet = buyNFTStatement.getBudgetWallet().getKey().getContent();
                String asset = buyNFTStatement.getBudgetAmount().getAsset().getContent();
                String tokenAddress = Token.getContractAddressByToken(buyNFTStatement.getBudgetAmount().getAsset());

                ArrayList<DependencyGraph.GraphNode> dependOn = walletAssetToIncrease.get(wallet + asset);
                if (dependOn == null) {
                    dependOn = new ArrayList<>();
                }
                DependencyGraph.GraphNode graphNode = new DependencyGraph.GraphNode(
                        triggerStatement, dependOn, new ArrayList<>());
                BigInteger amount = Calculator.calBinaryExp(
                        buyNFTStatement.getBudgetAmount().getBinaryExpression()).multiply(
                        new BigDecimal(Token.getTokenDecimals(tokenAddress))).toBigInteger();
                graphNode.getAssetDecrease().put(wallet + asset, amount);

                for (DependencyGraph.GraphNode father : dependOn) {
                    father.getDominateOver().add(graphNode);
                }
                if (dependOn.size() == 0) {
                    roots.add(graphNode);
                }
                walletAssetToDecrease.computeIfAbsent(wallet + asset, k -> new ArrayList<>());
                walletAssetToDecrease.get(wallet + asset).add(graphNode);
                allNodes.add(graphNode);

            } else if (statement instanceof Node.SellNFTStatement) {
                DependencyGraph.GraphNode graphNode = new DependencyGraph.GraphNode(
                        triggerStatement, new ArrayList<>(), new ArrayList<>());
                roots.add(graphNode);
                allNodes.add(graphNode);

            } else {
                throw new RuntimeException("Unknown statement.");
            }
        }

        return new DependencyGraph(roots, allNodes);
    }

    private static HashMap<String, BigInteger> mergeMapsAdd(HashMap<String, BigInteger> map1, HashMap<String, BigInteger> map2) {
        HashMap<String, BigInteger> mergedMap = new HashMap<>(map1);

        for (Map.Entry<String, BigInteger> entry : map2.entrySet()) {
            mergedMap.merge(entry.getKey(), entry.getValue(), BigInteger::add);
        }

        return mergedMap;
    }

    private static HashMap<String, BigInteger> mergeMapsSub(HashMap<String, BigInteger> map1, HashMap<String, BigInteger> map2) {
        HashMap<String, BigInteger> mergedMap = new HashMap<>(map1);

        for (Map.Entry<String, BigInteger> entry : map2.entrySet()) {
            mergedMap.merge(entry.getKey(), entry.getValue(), BigInteger::subtract);
        }

        return mergedMap;
    }

    private static BigInteger balanceOfAccount(String wallet, String asset) throws Exception {
        String tokenAddress = Token.getContractAddressByToken(new Word(asset, Type.ASSET));
        Web3j web3j = Web3jBuilder.buildWeb3j();
        if (asset.equals("ETH")) {
            EthGetBalance ethGetBalance = web3j.ethGetBalance(wallet, DefaultBlockParameterName.LATEST).send();
            return ethGetBalance.getBalance();
        }
        ContractFuncService erc20 = new ContractFuncService(
                tokenAddress,
                web3j,
                new ReadonlyTransactionManager(web3j, wallet),
                new DefaultGasProvider()
        );
        return erc20.balanceOf(wallet).send();
    }

//    private static void estimateAssetOfSingleNode(DependencyGraph.GraphNode graphNode) {
//        ArrayList<DependencyGraph.GraphNode> dependOn = graphNode.getDependOn();
//        ArrayList<DependencyGraph.GraphNode> dominateOver = graphNode.getDominateOver();
//        HashMap<String, BigInteger> assetIncrease = graphNode.getAssetIncrease();
//        HashMap<String, BigInteger> assetDecrease = graphNode.getAssetDecrease();
//
//        HashMap<String, BigInteger> estimatedAsset = new HashMap<>();
//
//        for (DependencyGraph.GraphNode dependNode : dependOn) {
//            estimatedAsset = mergeMapsAdd(estimatedAsset, dependNode.getEstimatedAsset());
//        }
//
//        estimatedAsset = mergeMapsAdd(estimatedAsset, assetIncrease);
//        estimatedAsset = mergeMapsSub(estimatedAsset, assetDecrease);
//
//        graphNode.setEstimatedAsset(estimatedAsset);
//
//        for (DependencyGraph.GraphNode dominateNode : dominateOver) {
//            estimateAssetOfSingleNode(dominateNode);
//        }
//    }

    private static void collectAncestorChanges(DependencyGraph.GraphNode node,
                                               HashMap<String, BigInteger> estimatedAsset,
                                               Set<DependencyGraph.GraphNode> visited) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);

        estimatedAsset = mergeMapsAdd(estimatedAsset, node.getAssetIncrease());
        estimatedAsset = mergeMapsSub(estimatedAsset, node.getAssetDecrease());

        for (DependencyGraph.GraphNode dependOnNode : node.getDependOn()) {
            collectAncestorChanges(dependOnNode, estimatedAsset, visited);
        }
    }

    private static void estimateAssetOfSingleNode(DependencyGraph.GraphNode graphNode) {
        ArrayList<DependencyGraph.GraphNode> dependOn = graphNode.getDependOn();
        // ArrayList<DependencyGraph.GraphNode> dominateOver = graphNode.getDominateOver();
        HashMap<String, BigInteger> assetIncrease = graphNode.getAssetIncrease();
        HashMap<String, BigInteger> assetDecrease = graphNode.getAssetDecrease();

        HashMap<String, BigInteger> estimatedAsset = new HashMap<>();
        Set<DependencyGraph.GraphNode> visited = new HashSet<>();

        for (DependencyGraph.GraphNode dependOnNode : dependOn) {
            collectAncestorChanges(dependOnNode, estimatedAsset, visited);
        }

        estimatedAsset = mergeMapsAdd(estimatedAsset, assetIncrease);
        estimatedAsset = mergeMapsSub(estimatedAsset, assetDecrease);

        graphNode.setEstimatedAsset(estimatedAsset);
    }

    private static void estimateAssetInGraph(DependencyGraph dependencyGraph) throws Exception {

        ArrayList<DependencyGraph.GraphNode> allNodes = dependencyGraph.getAllNodes();

        for (DependencyGraph.GraphNode graphNode : allNodes) {
            estimateAssetOfSingleNode(graphNode);
        }

        // 初始资产
        for (DependencyGraph.GraphNode graphNode : allNodes) {
            HashMap<String, BigInteger> estimatedAsset = graphNode.getEstimatedAsset();
            for (String walletAsset : estimatedAsset.keySet()) {
                String wallet = walletAsset.substring(0, 42);
                String asset = walletAsset.substring(42);

                estimatedAsset.put(walletAsset, estimatedAsset.get(walletAsset).
                        add(balanceOfAccount(wallet, asset)));
            }
            graphNode.setEstimatedAsset(estimatedAsset);
        }
    }


    private static void calHeightOfSingleNode(DependencyGraph.GraphNode graphNode, int height,
                                              HashMap<Integer, ArrayList<DependencyGraph.GraphNode>> heightToNodes) {
        heightToNodes.computeIfAbsent(height, k -> new ArrayList<>());
        ArrayList<DependencyGraph.GraphNode> graphNodesOnHeight = heightToNodes.get(height);

        if (graphNodesOnHeight.contains(graphNode)) {
            return;
        }
        graphNodesOnHeight.add(graphNode);

        if (graphNode.getDependOn() == null) {
            return;
        }
        for (DependencyGraph.GraphNode fathers : graphNode.getDependOn()) {
            calHeightOfSingleNode(fathers, height + 1, heightToNodes);
        }
    }

    private static HashMap<Integer, ArrayList<DependencyGraph.GraphNode>> calHeightOfNodes(
            ArrayList<DependencyGraph.GraphNode> allNodes) {
        HashMap<Integer, ArrayList<DependencyGraph.GraphNode>> heightToNodes = new HashMap<>();
        ArrayList<DependencyGraph.GraphNode> zeroHeightNode = new ArrayList<>();

        for (DependencyGraph.GraphNode graphNode : allNodes) {
            if (graphNode.getDominateOver() == null || graphNode.getDominateOver().size() == 0) {
                zeroHeightNode.add(graphNode);
            }
        }
        for (DependencyGraph.GraphNode graphNode : zeroHeightNode) {
            calHeightOfSingleNode(graphNode, 0, heightToNodes);
        }

        return heightToNodes;
    }

    private static ArrayList<DependencyGraph.GraphNode> knapsack(
            ArrayList<DependencyGraph.GraphNode> packages,
            HashMap<String, BigInteger> capacities, Set<String> careItemSet) {
        // capacities: 背包容量（物体类型 -> 最大数量）
        // packages: 每个包裹的物品数量列表 (包裹 -> 物体类型 -> 数量)

        // dp map，记录每个背包状态和其对应的最大包裹数
        Map<String, BigInteger> dp = new HashMap<>();
        // 记录背包状态下选择的包裹列表
        Map<String, ArrayList<DependencyGraph.GraphNode>> selectedPackages = new HashMap<>();

        // 初始化：容量为最大时，未选择包裹
        dp.put(createKey(capacities), BigInteger.ZERO);
        selectedPackages.put(createKey(capacities), new ArrayList<>());

        // 遍历每个包裹（GraphNode）
        for (DependencyGraph.GraphNode packageItem : packages) {
            // 获取当前包裹的物品和数量
            Map<String, BigInteger> packageContents = packageItem.getAssetIncrease();

            // 反向遍历 DP，防止多次选择同一个包裹
            List<String> currentStates = new ArrayList<>(dp.keySet());
            for (String stateKey : currentStates) {
                Map<String, BigInteger> state = parseKey(stateKey);

                // 更新后的背包状态
                HashMap<String, BigInteger> newState = new HashMap<>(state);
                boolean valid = true;

                // 检查是否可以加入当前包裹
                for (Map.Entry<String, BigInteger> entry : packageContents.entrySet()) {
                    String itemType = entry.getKey();
                    if (!careItemSet.contains(itemType)) {
                        continue;
                    }

                    BigInteger itemCount = entry.getValue();

                    BigInteger currentCapacity = newState.getOrDefault(itemType, BigInteger.ZERO);
                    BigInteger newCapacity = currentCapacity.add(itemCount);

                    // 检查新容量是否超出背包限制
                    if (newCapacity.compareTo(capacities.get(itemType)) > 0) {
                        valid = false;
                        break;
                    }
                    newState.put(itemType, newCapacity);
                }

                // 如果包裹适合当前背包配置，更新 dp
                if (valid) {
                    String newStateKey = createKey(newState);
                    BigInteger newValue = dp.get(stateKey).add(BigInteger.ONE);

                    // 比较最大包裹数
                    if (!dp.containsKey(newStateKey) || newValue.compareTo(dp.get(newStateKey)) > 0) {
                        dp.put(newStateKey, newValue);
                        // 更新选择的包裹列表
                        ArrayList<DependencyGraph.GraphNode> newPackageList =
                                new ArrayList<>(selectedPackages.get(stateKey));
                        newPackageList.add(packageItem);
                        selectedPackages.put(newStateKey, newPackageList);
                    }
                }
            }
        }

        // 找到最大包裹数的状态
        String bestStateKey = Collections.max(dp.entrySet(), Map.Entry.comparingByValue()).getKey();
        return selectedPackages.get(bestStateKey);
    }

    // 辅助函数：将当前背包状态转换为字符串键
    private static String createKey(Map<String, BigInteger> capacities) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, BigInteger> entry : capacities.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
        }
        return sb.toString();
    }

    // 辅助函数：从字符串键反向解析回背包状态
    private static Map<String, BigInteger> parseKey(String key) {
        Map<String, BigInteger> result = new HashMap<>();
        String[] parts = key.split(";");
        for (String part : parts) {
            if (!part.isEmpty()) {
                String[] kv = part.split(":");
                result.put(kv[0], new BigInteger(kv[1]));
            }
        }
        return result;
    }

    private static DependencyGraph optimizeDependencyGraph(DependencyGraph dependencyGraph) throws Exception {
        estimateAssetInGraph(dependencyGraph);
        ArrayList<DependencyGraph.GraphNode> roots = dependencyGraph.getRoots();
        ArrayList<DependencyGraph.GraphNode> allNodes = dependencyGraph.getAllNodes();
        boolean changed = true;

        while (changed) {
            changed = false;
            HashMap<Integer, ArrayList<DependencyGraph.GraphNode>> heightToNodes = calHeightOfNodes(allNodes);

            for (int height = 0; ; height++) {
                if (heightToNodes.get(height) == null) {
                    break;
                }
                ArrayList<DependencyGraph.GraphNode> nodesToHandle = heightToNodes.get(height);
                for (DependencyGraph.GraphNode graphNode : nodesToHandle) {
                    if (roots.contains(graphNode)) {
                        continue;
                    }

                    HashMap<String, BigInteger> estimatedAssetOfCurNode = graphNode.getEstimatedAsset();
                    ArrayList<DependencyGraph.GraphNode> fathers = graphNode.getDependOn();
                    Set<String> assetToDecrease = graphNode.getAssetDecrease().keySet();

                    ArrayList<DependencyGraph.GraphNode> fatherToAbort = knapsack(
                            fathers, estimatedAssetOfCurNode, assetToDecrease);
                    if (fatherToAbort != null && fatherToAbort.size() > 0) {
                        changed = true;
                    } else {
                        continue;
                    }

                    for (DependencyGraph.GraphNode father : fatherToAbort) {
                        graphNode.getDependOn().remove(father);
                        father.getDominateOver().remove(graphNode);

                        for (DependencyGraph.GraphNode grandFather : father.getDependOn()) {
                            if (!graphNode.getDependOn().contains(grandFather)) {
                                graphNode.getDependOn().add(grandFather);
                            }
                            if (!grandFather.getDominateOver().contains(graphNode)) {
                                grandFather.getDominateOver().add(graphNode);
                            }
                        }

                        graphNode.setEstimatedAsset(mergeMapsSub(
                                graphNode.getEstimatedAsset(), father.getAssetIncrease()));

                        for (DependencyGraph.GraphNode son : graphNode.getDominateOver()) {
                            if (!son.getDependOn().contains(father)) {
                                son.getDependOn().add(father);
                            }
                            if (!father.getDominateOver().contains(son)) {
                                father.getDominateOver().add(son);
                            }
                        }
                    }

                }
            }

        }
        return dependencyGraph;
    }

    private static DependencyGraph repairPossibleDependency(DependencyGraph dependencyGraph) {
        ArrayList<DependencyGraph.GraphNode> allNodes = dependencyGraph.getAllNodes();

        for (DependencyGraph.GraphNode graphNode : allNodes) {
            Node.Statement statement = graphNode.getSelf().getStatement();
            if (statement instanceof Node.BorrowStatement borrowStatement) {
                String borrowForWallet = borrowStatement.getForWallet().getKey().getContent();

                for (DependencyGraph.GraphNode anotherNode : allNodes) {
                    Node.Statement anotherStatement = anotherNode.getSelf().getStatement();
                    if (anotherStatement instanceof Node.StakeStatement stakeStatement) {
                        if (anotherNode.getDependOn().contains(graphNode)) {
                            // Situation: borrow tokens to stake
                            continue;
                        }

                        if (stakeStatement.getWallet().getKey().getContent().equals(borrowForWallet)) {
                            if (!graphNode.getDependOn().contains(anotherNode)) {
                                graphNode.getDependOn().add(anotherNode);
                            }
                            if (!anotherNode.getDominateOver().contains(graphNode)) {
                                anotherNode.getDominateOver().add(graphNode);
                            }
                        }
                    }
                }
            }
        }

        return dependencyGraph;
    }

    private static void graphVisualize(DependencyGraph dependencyGraph) {
        System.setProperty("org.graphstream.ui", "swing");
        // 创建一个空的图
        Graph graph = new SingleGraph("DependencyGraph");
        // 为每个节点创建一个图形节点
        for (DependencyGraph.GraphNode node : dependencyGraph.getAllNodes()) {
            // 每个 GraphNode 对应图中的一个节点，使用节点的唯一标识符
            graph.addNode(node.getSelf().toString());
        }
        // 为每个节点的依赖关系添加边
        for (DependencyGraph.GraphNode node : dependencyGraph.getAllNodes()) {
//            // 添加从依赖节点（dependOn）到当前节点的边
//            for (DependencyGraph.GraphNode depNode : node.getDependOn()) {
//                String edgeId = depNode.getSelf().toString() + "->" + node.getSelf().toString();
//                graph.addEdge(edgeId, depNode.getSelf().toString(), node.getSelf().toString(), true);
//            }

            // 添加从当前节点到其支配节点（dominateOver）的边
            for (DependencyGraph.GraphNode depNode : node.getDominateOver()) {
                String edgeId = node.getSelf().toString() + "->" + depNode.getSelf().toString();
                graph.addEdge(edgeId, node.getSelf().toString(), depNode.getSelf().toString(), true);
            }
        }

        // 设置节点的样式
        for (org.graphstream.graph.Node gsNode : graph) {
            gsNode.setAttribute("ui.label", gsNode.getId());
        }

        // 显示图
        Viewer viewer = graph.display();
        viewer.enableAutoLayout();  // 自动布局
    }


}
