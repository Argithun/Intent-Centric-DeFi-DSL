package transaction;

import ast.Node;
import ast.Word;
import infrastrcuture.ContractFuncService;
import infrastrcuture.QueryService;
import infrastrcuture.Token;
import okhttp3.*;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.StaticArray2;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.request.Transaction;
import settings.Settings;
import settings.ContractAddress;
import tool.Calculator;
import tool.Signature;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;


public class TransGenerator {
    private final Node.Statement statement;
    private Transaction preTransaction;
    private Transaction transaction;
    private String routerAddress;
    private boolean constructSuccess;

    public TransGenerator(Node.Statement statement) {
        this.statement = statement;
        this.preTransaction = null;
        this.transaction = null;
        this.routerAddress = null;
        this.constructSuccess = true;
    }

    public Transaction getPreTransaction() {
        return preTransaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getRouterAddress() {
        return routerAddress;
    }

    public boolean getConstructSuccess() {
        return constructSuccess;
    }

    public void genTransaction() throws Exception {
        if (statement instanceof Node.TransferStatement) {
            genTransferTransaction();
        } else if (statement instanceof Node.BorrowStatement) {
            genBorrowTransaction();
        } else if (statement instanceof Node.RepayBorrowStatement) {
            genRepayTransaction();
        } else if (statement instanceof Node.SwapStatement) {
            genSwapTransaction();
        } else if (statement instanceof Node.AddLiquidityStatement) {
            genAddLiquidityTransaction();
        } else if (statement instanceof Node.RemoveLiquidityStatement) {
            genRemoveLiquidityTransaction();
        } else if (statement instanceof Node.StakeStatement) {
            genStakeTransaction();
        } else if (statement instanceof Node.SellNFTStatement) {
            genSellNFTTransaction();
        } else if (statement instanceof Node.BuyNFTStatement) {
            genBuyNFTTransaction();
        } else {
            System.out.println("Unsupported type of transaction statement: " + statement);
            this.constructSuccess = false;
        }
    }

    private void genTransferTransaction() throws Exception {
        Node.TransferStatement transferStatement = (Node.TransferStatement) statement;

        String fromWallet = transferStatement.getFromWallet().getKey().getContent();
        String toWallet = transferStatement.getToWallet().getKey().getContent();
        String tokenAddress = Token.getContractAddressByToken(transferStatement.getAmount().getAsset());
        BigDecimal tokenNum = Calculator.calBinaryExp(transferStatement.getAmount().getBinaryExpression());

        // Convert token amount to the smallest unit
        BigInteger tokenAmount = tokenNum.multiply(new BigDecimal(Token.getTokenDecimals(tokenAddress))).toBigInteger();
        BigInteger nonce = null;

        // Create the ERC-20 transfer function data
        Function function = new Function(
                "transfer",
                Arrays.asList(new Address(toWallet), new Uint256(tokenAmount)),
                Collections.emptyList());
        String encodedFunction = FunctionEncoder.encode(function);

        // Example gas price and limit (you can fetch or calculate dynamically)
        BigInteger gasPrice = QueryService.getGasPrice();
        BigInteger gasLimit = Settings.DEFAULT_GAS_LIMIT;

        // Create the Transaction object
        this.transaction = Transaction.createFunctionCallTransaction(
                fromWallet,
                nonce,
                gasPrice,
                gasLimit,
                tokenAddress,
                encodedFunction
        );
    }

    private void genBorrowTransaction() throws Exception {
        Node.BorrowStatement borrowStatement = (Node.BorrowStatement) statement;

        String borrowTokenAddress = Token.getContractAddressByToken(borrowStatement.getBorrowAmount().getAsset());
        BigDecimal borrowTokenNum = Calculator.calBinaryExp(borrowStatement.getBorrowAmount().getBinaryExpression());
        String borrowForWallet = borrowStatement.getForWallet().getKey().getContent();
        String platform = borrowStatement.getPlatform().getContent();
        String collateralTokenAddress = Token.getContractAddressByToken(borrowStatement.getCollateralAmount().getAsset());
        BigDecimal collateralTokenNum = Calculator.calBinaryExp(borrowStatement.getCollateralAmount().getBinaryExpression());
        String collateralWallet = borrowStatement.getCollateralWallet().getKey().getContent();

        if (!platform.equals("Aave") && !platform.equals("Compound")) {
            System.out.println("Unsupported platform for borrowing: " + platform);
            this.constructSuccess = false;
            return;
        }

        // Convert borrow and collateral amounts to smallest units
        BigInteger borrowAmount = borrowTokenNum.multiply(
                new BigDecimal(Token.getTokenDecimals(borrowTokenAddress))).toBigInteger();
        BigInteger collateralAmount = collateralTokenNum.multiply(
                new BigDecimal(Token.getTokenDecimals(collateralTokenAddress))).toBigInteger();

        // Prepare transaction data based on the platform
        if (platform.equals("Aave")) {
            // AAVE Borrow: Data for `borrow` method
            Function borrowFunction = new Function(
                    "borrow",
                    Arrays.asList(
                            new Address(borrowTokenAddress),  // Asset to borrow
                            new Uint256(borrowAmount),        // Borrow amount
                            new Uint256(2),                   // Interest rate mode (2 = Variable)
                            new Uint256(0),                   // Referral code
                            new Address(borrowForWallet)      // On behalf of
                    ),
                    Collections.emptyList()
            );
            String encodedFunction = FunctionEncoder.encode(borrowFunction);

            this.transaction = Transaction.createFunctionCallTransaction(
                    collateralWallet,  // From wallet (collateral provider)
                    null,              // Nonce
                    QueryService.getGasPrice(), // Example gas price (20 Gwei)
                    BigInteger.valueOf(300000),         // Example gas limit
                    ContractAddress.AAVE_LENDING_POOL,  // AAVE LendingPool address (Mainnet)
                    encodedFunction
            );
        } else {
            // COMPOUND Borrow: Data for `borrow` method
            Function borrowFunction = new Function(
                    "borrow",
                    Arrays.asList(new Uint256(borrowAmount)), // Borrow amount
                    Collections.emptyList()
            );
            String encodedFunction = FunctionEncoder.encode(borrowFunction);

            this.transaction = Transaction.createFunctionCallTransaction(
                    collateralWallet,  // From wallet (collateral provider)
                    null,              // Nonce
                    QueryService.getGasPrice(), // Example gas price (20 Gwei)
                    BigInteger.valueOf(300000),         // Example gas limit
                    borrowTokenAddress,                 // cToken address to borrow from
                    encodedFunction
            );
        }
    }

    private void genRepayTransaction() throws Exception {
        Node.RepayBorrowStatement repayBorrowStatement = (Node.RepayBorrowStatement) statement;

        String repayTokenAddress = Token.getContractAddressByToken(repayBorrowStatement.getAmount().getAsset());
        BigDecimal repayTokenNum = Calculator.calBinaryExp(repayBorrowStatement.getAmount().getBinaryExpression());
        BigInteger repayTokenAmount = repayTokenNum.multiply(
                new BigDecimal(Token.getTokenDecimals(repayTokenAddress))).toBigInteger();
        String fromWallet = repayBorrowStatement.getWallet().getKey().getContent();
        String platform = repayBorrowStatement.getPlatform().getContent();

        if (!platform.equals("Aave") && !platform.equals("Compound")) {
            System.out.println("Unsupported platform for borrowing: " + platform);
            this.constructSuccess = false;
            return;
        }

        BigInteger gasPrice = QueryService.getGasPrice();
        BigInteger gasLimit = Settings.DEFAULT_GAS_LIMIT;

        if (platform.equals("Aave")) {
            // AAVE: 调用 LendingPool 的 repay 方法进行偿还
            String lendingPoolAddress = ContractAddress.AAVE_LENDING_POOL; // AAVE LendingPool 地址（Mainnet）

            Function repayFunction = new Function(
                    "repay",
                    Arrays.asList(
                            new Address(repayTokenAddress),   // 偿还的资产地址
                            new Uint256(repayTokenAmount),    // 偿还的资产数量
                            new Address(fromWallet),          // 偿还的用户地址
                            new Uint256(2)                    // Interest rate mode (2 = Variable)
                    ),
                    Collections.emptyList()
            );
            String repayData = FunctionEncoder.encode(repayFunction);

            this.transaction = Transaction.createFunctionCallTransaction(
                    fromWallet,
                    null,
                    gasPrice,
                    gasLimit,
                    lendingPoolAddress,
                    repayData
            );

        } else {
            // COMPOUND: 调用 cToken 的 repayBorrow 方法进行偿还
            Function repayBorrowFunction = new Function(
                    "repayBorrow",
                    Arrays.asList(new Uint256(repayTokenAmount)), // 偿还的资产数量
                    Collections.emptyList()
            );
            String repayData = FunctionEncoder.encode(repayBorrowFunction);

            this.transaction = Transaction.createFunctionCallTransaction(
                    fromWallet,
                    null,
                    gasPrice,
                    gasLimit,
                    repayTokenAddress,
                    repayData
            );
        }

    }


    private void genSwapTransaction() throws Exception {
        Node.SwapStatement swapStatement = (Node.SwapStatement) statement;

        String wallet = swapStatement.getWallet().getKey().getContent();
        String inTokenAddress = Token.getContractAddressByToken(swapStatement.getAmount().getAsset());
        BigDecimal inTokenNum = Calculator.calBinaryExp(swapStatement.getAmount().getBinaryExpression());
        BigInteger inTokenAmount = inTokenNum.multiply(new BigDecimal(Token.getTokenDecimals(inTokenAddress))).toBigInteger();
        String outTokenAddress = Token.getContractAddressByToken(swapStatement.getAsset());
        String platform = swapStatement.getPlatform().getContent();

        if (!platform.equals("Uniswap") && !platform.equals("Sushiswap")
                && !platform.equals("Curve") && !platform.equals("1inch")) {
            System.out.println("Unsupported platform for swapping: " + platform);
            this.constructSuccess = false;
            return;
        }

        BigInteger gasPrice = QueryService.getGasPrice();
        BigInteger gasLimit = Settings.DEFAULT_GAS_LIMIT;

        if (platform.equals("Uniswap") || platform.equals("Sushiswap")) {
            // UNISWAP/SUSHISWAP: 使用 swapExactTokensForTokens 方法
            routerAddress = platform.equals("Uniswap")
                    ? ContractAddress.UNISWAP_ROUTER_V3 // Uniswap RouterV3 地址
                    : ContractAddress.SUSHISWAP_ROUTER; // SushiSwap Router 地址

            StaticArray2<Type> path = new StaticArray2<>(
                    new Address(inTokenAddress),
                    new Address(outTokenAddress)
            );


            List<Type> inputParameters = Arrays.asList(
                    new Uint256(inTokenAmount),                     // 输入代币数量
                    new Uint256(0),                           // 最小输出代币数量，设置为 0 表示允许任意滑点
                    path,                                          // 代币路径
                    new Address(wallet),                            // 接收地址
                    new Uint256(BigInteger.valueOf(System.currentTimeMillis() / 1000 + 300)) // 截止时间（当前时间+300秒）
            );

            Function swapFunction = new Function(
                    "swapExactTokensForTokens",
                    inputParameters,
                    Collections.emptyList()
            );
            String swapData = FunctionEncoder.encode(swapFunction);

            transaction = Transaction.createFunctionCallTransaction(
                    wallet,
                    null,
                    gasPrice,
                    gasLimit,
                    routerAddress,
                    swapData
            );

        } else if (platform.equals("Curve")) {
            // CURVE: 使用 exchange 方法（假设使用 3pool 示例）
            String curvePoolAddress = ContractAddress.CURVE_3POOL; // Curve 3pool 合约地址
            routerAddress = curvePoolAddress;
            int inTokenIndex = 0; // 代币索引（需要根据具体池的配置确认）
            int outTokenIndex = 1; // 目标代币索引

            Function exchangeFunction = new Function(
                    "exchange",
                    Arrays.asList(
                            new Uint256(inTokenIndex),         // 输入代币索引
                            new Uint256(outTokenIndex),       // 输出代币索引
                            new Uint256(inTokenAmount),       // 输入代币数量
                            new Uint256(0)                    // 最小输出代币数量
                    ),
                    Collections.emptyList()
            );
            String exchangeData = FunctionEncoder.encode(exchangeFunction);

            transaction = Transaction.createFunctionCallTransaction(
                    wallet,
                    null,
                    gasPrice,
                    gasLimit,
                    curvePoolAddress,
                    exchangeData
            );

        } else {
            // 1INCH: 使用 API 获取交易路径，然后调用合约执行
            String oneInchRouterAddress = ContractAddress.ONEINCH_ROUTER; // 1inch Router 地址
            routerAddress = oneInchRouterAddress;
            // 调用 swap 方法
            Function oneInchSwapFunction = new Function(
                    "swap",
                    Arrays.asList(
                            new Address(inTokenAddress),     // 输入代币地址
                            new Address(outTokenAddress),    // 输出代币地址
                            new Uint256(inTokenAmount),      // 输入代币数量
                            new Uint256(1),                  // 最小输出代币数量，设置为 1 表示允许任意滑点
                            new Address(wallet)              // 接收地址
                    ),
                    Collections.emptyList()
            );
            String oneInchSwapData = FunctionEncoder.encode(oneInchSwapFunction);

            transaction = Transaction.createFunctionCallTransaction(
                    wallet,
                    null,
                    gasPrice,
                    gasLimit,
                    oneInchRouterAddress,
                    oneInchSwapData
            );
        }

    }

    private void genAddLiquidityTransaction() throws Exception {
        Node.AddLiquidityStatement addLiquidityStatement = (Node.AddLiquidityStatement) statement;

        ArrayList<String> wallets = new ArrayList<>();
        ArrayList<String> inTokenAddresses = new ArrayList<>();
        ArrayList<BigInteger> inTokenAmounts = new ArrayList<>();
        String platform = addLiquidityStatement.getPlatform().getContent();

        for (Node.Wallet wallet : addLiquidityStatement.getWallets()) {
            wallets.add(wallet.getKey().getContent());
        }
        for (Node.Amount amount : addLiquidityStatement.getAmounts()) {
            String inTokenAddress = Token.getContractAddressByToken(amount.getAsset());
            inTokenAddresses.add(inTokenAddress);
            BigDecimal inTokenNum = Calculator.calBinaryExp(amount.getBinaryExpression());
            inTokenAmounts.add(inTokenNum.multiply(new BigDecimal(Token.getTokenDecimals(inTokenAddress))).toBigInteger());
        }

        BigInteger gasPrice = QueryService.getGasPrice();
        BigInteger gasLimit = Settings.DEFAULT_GAS_LIMIT; // 设定合理的 Gas 限制


        if (platform.equals("Uniswap") || platform.equals("Sushiswap")) {
            // Uniswap/SushiSwap: 使用 addLiquidity 或 addLiquidityETH
            String routerAddress = platform.equals("Uniswap")
                    ? ContractAddress.UNISWAP_ROUTER_V3 // Uniswap RouterV3 地址
                    : ContractAddress.SUSHISWAP_ROUTER; // SushiSwap Router 地址

            // 默认取第一个钱包作为交易发起者
            String fromWallet = wallets.get(0);

            Function addLiquidityFunction = new Function(
                    "addLiquidity",
                    Arrays.asList(
                            new Address(inTokenAddresses.get(0)),       // 代币A地址
                            new Address(inTokenAddresses.get(1)),       // 代币B地址
                            new Uint256(inTokenAmounts.get(0)),         // 代币A数量
                            new Uint256(inTokenAmounts.get(1)),         // 代币B数量
                            new Uint256(0),                             // 最小代币A数量
                            new Uint256(0),                             // 最小代币B数量
                            new Address(fromWallet),                    // 流动性代币接收地址
                            new Uint256(BigInteger.valueOf(System.currentTimeMillis() / 1000 + 300)) // 截止时间
                    ),
                    Collections.emptyList()
            );
            String addLiquidityData = FunctionEncoder.encode(addLiquidityFunction);

            transaction = Transaction.createFunctionCallTransaction(
                    fromWallet,
                    null,
                    gasPrice,
                    gasLimit,
                    routerAddress,
                    addLiquidityData
            );

        } else {
            System.out.println("Unexpected error for platform: " + platform);
            this.constructSuccess = false;
            return;
        }
    }

    private void genRemoveLiquidityTransaction() throws Exception {
        Node.RemoveLiquidityStatement removeLiquidityStatement = (Node.RemoveLiquidityStatement) statement;

        ArrayList<String> wallets = new ArrayList<>();
        ArrayList<String> outTokenAddresses = new ArrayList<>();
        ArrayList<BigInteger> outTokenAmounts = new ArrayList<>();
        String platform = removeLiquidityStatement.getPlatform().getContent();

        for (Node.Wallet wallet : removeLiquidityStatement.getWallets()) {
            wallets.add(wallet.getKey().getContent());
        }
        for (Node.Amount amount : removeLiquidityStatement.getAmounts()) {
            String outTokenAddress = Token.getContractAddressByToken(amount.getAsset());
            outTokenAddresses.add(outTokenAddress);
            BigDecimal outTokenNum = Calculator.calBinaryExp(amount.getBinaryExpression());
            outTokenAmounts.add(outTokenNum.multiply(new BigDecimal(Token.getTokenDecimals(outTokenAddress))).toBigInteger());
        }

        // 示例 Gas 参数
        BigInteger gasPrice = QueryService.getGasPrice(); // 20 Gwei
        BigInteger gasLimit = Settings.DEFAULT_GAS_LIMIT; // 设定合理的 Gas 限制

        // 默认取第一个钱包作为交易发起者
        String fromWallet = wallets.get(0);

        if (platform.equals("Uniswap") || platform.equals("Sushiswap")) {
            // 获取 Router 地址
            String routerAddress = platform.equals("Uniswap")
                    ? ContractAddress.UNISWAP_ROUTER_V3 // Uniswap RouterV3 地址
                    : ContractAddress.SUSHISWAP_ROUTER; // SushiSwap Router 地址

            // 构建 removeLiquidity 的函数调用
            Function removeLiquidityFunction = new Function(
                    "removeLiquidity",
                    Arrays.asList(
                            new Address(outTokenAddresses.get(0)),       // 代币A地址
                            new Address(outTokenAddresses.get(1)),       // 代币B地址
                            new Uint256(outTokenAmounts.get(0)),         // 撤回代币A数量
                            new Uint256(outTokenAmounts.get(1)),         // 撤回代币B数量
                            new Uint256(0),                              // 最小代币A数量
                            new Uint256(0),                              // 最小代币B数量
                            new Address(fromWallet),                     // 流动性代币接收地址
                            new Uint256(BigInteger.valueOf(System.currentTimeMillis() / 1000 + 300)) // 截止时间
                    ),
                    Collections.emptyList()
            );

            // 编码数据
            String removeLiquidityData = FunctionEncoder.encode(removeLiquidityFunction);

            // 构建交易
            transaction = Transaction.createFunctionCallTransaction(
                    fromWallet,
                    null,
                    gasPrice,
                    gasLimit,
                    routerAddress,
                    removeLiquidityData
            );
        } else {
            System.out.println("Unexpected error for platform: " + platform);
            this.constructSuccess = false;
            return;
        }

    }

    private void genStakeTransaction() throws Exception {
        Node.StakeStatement stakeStatement = (Node.StakeStatement) statement;

        String stakeTokenAddress = Token.getContractAddressByToken(stakeStatement.getAmount().getAsset());
        BigDecimal stakeTokenNum = Calculator.calBinaryExp(stakeStatement.getAmount().getBinaryExpression());
        BigInteger stakeTokenAmount = stakeTokenNum.multiply(
                new BigDecimal(Token.getTokenDecimals(stakeTokenAddress))).toBigInteger();
        String stakeWallet = stakeStatement.getWallet().getKey().getContent();
        ArrayList<String> strategy = stakeStatement.getStrategy();

        BigInteger gasPrice = QueryService.getGasPrice();
        BigInteger gasLimit = Settings.DEFAULT_GAS_LIMIT; // 设定合理的 Gas 限制

        BigInteger stakingDuration = new BigInteger(String.valueOf(30 * 24 * 60 * 60));

        if (strategy.contains("short-term")) {
            stakingDuration = new BigInteger(String.valueOf(30 * 24 * 60 * 60));
        } else if (strategy.contains("middle-term")) {
            stakingDuration = new BigInteger(String.valueOf(90 * 24 * 60 * 60));
        } else if (strategy.contains("long-term")) {
            stakingDuration = new BigInteger(String.valueOf(365 * 24 * 60 * 60));
        }

        // Aave,        6%
        // MakerDAO,    8%
        // Compound,    10%

        if (strategy.contains("low-risk")) {
            // AAVE: 调用 LendingPool 的 deposit 方法进行质押
            String lendingPoolAddress = ContractAddress.AAVE_LENDING_POOL; // AAVE LendingPool 地址（Mainnet）

            Function depositFunction = new Function(
                    "deposit",
                    Arrays.asList(
                            new Address(stakeTokenAddress),   // 要质押的资产地址
                            new Uint256(stakeTokenAmount),    // 要质押的资产数量
                            new Address(stakeWallet),         // 用户地址
                            new Uint256(0)                    // Referral code (0)
                    ),
                    Collections.emptyList()
            );
            String depositData = FunctionEncoder.encode(depositFunction);

            this.transaction = Transaction.createFunctionCallTransaction(
                    stakeWallet,
                    null,
                    gasPrice,
                    gasLimit,
                    lendingPoolAddress,
                    depositData
            );
        } else if (strategy.contains("middle-risk")) {
            assert (stakeStatement.getAmount().getAsset().getContent().equals("DAI"));

            // MakerDAO: 调用 DSR（Dai Savings Rate）合约的 join 方法进行质押
            String dsrContractAddress = ContractAddress.MAKERDAO_DSR; // 替换为实际 DSR 合约地址

            // 调用 MakerDAO 的 join 方法
            Function joinFunction = new Function(
                    "join",
                    Arrays.asList(
                            new Address(stakeWallet),         // 用户地址
                            new Uint256(stakeTokenAmount)     // 要质押的资产数量
                    ),
                    Collections.emptyList()
            );
            String joinData = FunctionEncoder.encode(joinFunction);

            this.transaction = Transaction.createFunctionCallTransaction(
                    stakeWallet,
                    null,
                    gasPrice,
                    gasLimit,
                    dsrContractAddress,
                    joinData
            );
        } else if (strategy.contains("high-risk")) {
            // COMPOUND: 调用 cToken 的 mint 方法进行质押
            String cTokenAddress = ContractAddress.COMPOUND_CETH; // 替换为对应的 cToken 地址（如 cETH）

            // 步骤 1: 调用原始资产的 approve 方法，授权 cToken 合约提取资产
            Function approveFunction = new Function(
                    "approve",
                    Arrays.asList(
                            new Address(cTokenAddress),   // cToken 合约地址
                            new Uint256(stakeTokenAmount) // 授权的资产数量
                    ),
                    Collections.emptyList()
            );
            String approveData = FunctionEncoder.encode(approveFunction);

            this.preTransaction = Transaction.createFunctionCallTransaction(
                    stakeWallet,
                    null,
                    gasPrice,
                    gasLimit,
                    stakeTokenAddress, // 原始资产的地址
                    approveData
            );

            // 步骤 2: 调用 cToken 合约的 mint 方法进行质押
            Function mintFunction = new Function(
                    "mint",
                    Collections.emptyList(), // mint 方法无参数
                    Collections.emptyList()
            );
            String mintData = FunctionEncoder.encode(mintFunction);

            this.transaction = Transaction.createFunctionCallTransaction(
                    stakeWallet,
                    null,
                    gasPrice,
                    gasLimit,
                    cTokenAddress, // cToken 合约地址
                    mintData
            );
        } else {
            // AAVE: 调用 LendingPool 的 deposit 方法进行质押
            String lendingPoolAddress = ContractAddress.AAVE_LENDING_POOL;  // AAVE LendingPool 地址（Mainnet）

            Function depositFunction = new Function(
                    "deposit",
                    Arrays.asList(
                            new Address(stakeTokenAddress),   // 要质押的资产地址
                            new Uint256(stakeTokenAmount),    // 要质押的资产数量
                            new Address(stakeWallet),         // 用户地址
                            new Uint256(0)              // Referral code (0)
                    ),
                    Collections.emptyList()
            );
            String depositData = FunctionEncoder.encode(depositFunction);

            this.transaction = Transaction.createFunctionCallTransaction(
                    stakeWallet,
                    null,
                    gasPrice,
                    gasLimit,
                    lendingPoolAddress,
                    depositData
            );
        }

    }

    private void genBuyNFTTransaction() throws Exception {
        // 提取 BuyNFTStatement 中的相关信息
        Node.BuyNFTStatement buyNFTStatement = (Node.BuyNFTStatement) this.statement;

        // 获取预算信息
        String budgetTokenAddress = Token.getContractAddressByToken(buyNFTStatement.getBudgetAmount().getAsset()); // ERC20 合约地址
        BigDecimal budgetTokenNum = Calculator.calBinaryExp(buyNFTStatement.getBudgetAmount().getBinaryExpression());
        BigInteger tokenDecimals = Token.getTokenDecimals(budgetTokenAddress);
        BigInteger budgetTokenAmount = budgetTokenNum.multiply(new BigDecimal(tokenDecimals)).toBigInteger(); // 支付的代币数量

        String budgetWallet = buyNFTStatement.getBudgetWallet().getKey().getContent();
        String NFTPlatform = buyNFTStatement.getNFTPlatform().getContent();

        // 获取 NFT 限定条件
        ArrayList<String> NFTQualifiers = buyNFTStatement.getNFTQualifiers();

        // 获取符合限定条件的 NFT 集合信息
        // List: contract addr, contract name, lowest price (ETH), average price (ETH)
        ArrayList<String> NFTInfo = QueryService.selectNFTCollection(NFTQualifiers);

        // 根据查询结果提取合约地址等信息
        String nftContractAddress = NFTInfo.get(0); // NFT 合约地址
        String nftCollectionSlug = QueryService.getOpenseaSlug("ethereum", nftContractAddress);
        String nftTokenIds = ""; // NFT tokenId，需要通过指定 API 获取实际值

        BigInteger maxTokenAmount = new BigDecimal(NFTInfo.get(3)).multiply(
                BigDecimal.valueOf(Token.calculateExchangeRate(buyNFTStatement.getBudgetAmount().getAsset(), new Word("ETH", ast.Type.ASSET)))).multiply(
                        new BigDecimal(tokenDecimals)).toBigInteger();
        BigInteger minTokenAmount = new BigDecimal(NFTInfo.get(2)).multiply(
                BigDecimal.valueOf(Token.calculateExchangeRate(buyNFTStatement.getBudgetAmount().getAsset(), new Word("ETH", ast.Type.ASSET)))).multiply(
                new BigDecimal(tokenDecimals)).toBigInteger();

        if(minTokenAmount.compareTo(budgetTokenAmount) >= 0) {
            System.out.println("Not sufficient budget for NFT purchase.");
            this.constructSuccess = false;
            return;
        }

        maxTokenAmount = maxTokenAmount.compareTo(budgetTokenAmount) > 0 ? budgetTokenAmount : maxTokenAmount;

        BigInteger counter = QueryService.getCounter(budgetWallet);

        // 构建 OpenSea Criteria Offer API 的请求体
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");

        String protocol_data_parameters = String.format(
                "{"
                        + "    \"orderType\":0,"
                        + "    \"offer\":["
                        + "      {"
                        + "        \"itemType\":1," // ERC20
                        + "        \"token\":\"%s\","
                        + "        \"identifierOrCriteria\":0,"
                        + "        \"startAmount\":%s,"
                        + "        \"endAmount\":%s"
                        + "      }"
                        + "    ],"
                        + "    \"offerer\":\"%s\","
                        + "    \"startTime\":%s,"
                        + "    \"endTime\":%s,"
                        + "    \"zone\":\"0x0000000000000000000000000000000000000000\","
                        + "    \"zoneHash\":\"0x0000000000000000000000000000000000000000000000000000000000000000\","
                        + "    \"salt\":\"%s\","
                        + "    \"conduitKey\":\"0x0000000000000000000000000000000000000000000000000000000000000000\","
                        + "    \"totalOriginalConsiderationItems\":1,"
                        + "    \"counter\":\"%s\""
                        + "  }",
                budgetTokenAddress,
                minTokenAmount,
                maxTokenAmount,
                budgetWallet,
                System.currentTimeMillis() / 1000, // 当前时间（Unix 时间戳）
                (System.currentTimeMillis() / 1000) + 7200, // 2h后到期
                UUID.randomUUID(),
                counter
        );

        String signature = Signature.signPrefixedMessage(protocol_data_parameters, Settings.ACCOUNT_PRIVATE_KEY);

        String requestBody = String.format(
                "{"
                        + "\"protocol_data\":{"
                        + "  \"parameters\":"
                        + protocol_data_parameters
                        + ",  \"signature\":\"%s\""
                        + "},"
                        + "\"criteria\":{"
                        + "  \"collection\":{"
                        + "    \"slug\":\"%s\""
                        + "  },"
                        + "  \"contract\":{"
                        + "    \"address\":\"%s\""
                        + "  },"
                        + "  \"encoded_token_ids\":\"%s\"" // 指定购买的具体 tokenId（如适用）
                        + "},"
                        + "\"protocol_address\":\"%s\""
                        + "}",
                signature,
                nftCollectionSlug,
                nftContractAddress,
                nftTokenIds,
                ContractAddress.OPENSEA_NFT_EXCHANGE
        );

        // 创建请求对象
        RequestBody body = RequestBody.create(mediaType, requestBody);
        Request request = new Request.Builder()
                .url("https://api.opensea.io/api/v2/offers")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .build();

        // 发送请求并获取响应
        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            System.out.println("NFT purchase offer created successfully: " + response.body().string());
        } else {
            System.out.println("Failed to create NFT purchase offer: " + response.body().string());
            this.constructSuccess = false;
            return;
        }
    }



    private void genSellNFTTransaction() {
        // todo https://docs.opensea.io/reference/post_listing
    }

}