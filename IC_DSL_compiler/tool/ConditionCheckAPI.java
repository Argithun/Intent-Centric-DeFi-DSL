package tool;

import ast.Node;
import ast.Type;
import ast.Word;

import infrastrcuture.ContractFuncService;
import infrastrcuture.Token;
import infrastrcuture.Web3jBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.StaticArray2;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ConditionCheckAPI {

    //--------------------------------------------------- Use API to check (trigger) ---------------------------------------------------------//
    public static boolean balanceCheckAPI(String walletKey, BigDecimal threshold, Word token, Type comparisonOperation) {
        assert (token.getType().equals(Type.ASSET));

        Web3j web3j = Web3jBuilder.buildWeb3j();

        String tokenName = token.getContent();

        try {
            // 获取 walletKey 对应账户的 tokenName 代币余额，并使用 comparisonOperation 操作符与 threshold 比较
            BigDecimal balance;

            if (tokenName.equals("ETH")) {
                EthGetBalance ethGetBalance = web3j.ethGetBalance(walletKey, DefaultBlockParameterName.LATEST).send();
                balance = new BigDecimal(ethGetBalance.getBalance());  // 将余额从 BigInteger 转为 BigDecimal
            } else {
                String tokenAddress = Token.getContractAddressByToken(token);
                if (tokenAddress == null) {
                    throw new IllegalArgumentException("Unknown token: " + tokenName);
                }

                ContractFuncService erc20 = new ContractFuncService(
                        tokenAddress,
                        web3j,
                        new ReadonlyTransactionManager(web3j, walletKey),
                        new DefaultGasProvider()
                );

                BigInteger tokenBalance = erc20.balanceOf(walletKey).send();
                balance = new BigDecimal(tokenBalance);  // 将余额从 BigInteger 转为 BigDecimal
            }

            // 以太坊使用18位精度，统一处理
            balance = balance.divide(BigDecimal.TEN.pow(18), 18, RoundingMode.DOWN);  // 除以 10^18，以太坊的标准精度
            boolean ret = false;

            // balance op threshold
            switch (comparisonOperation) {
                case GT:
                    ret = balance.compareTo(threshold) > 0;
                    break;
                case GE:
                    ret = balance.compareTo(threshold) >= 0;
                    break;
                case LT:
                    ret = balance.compareTo(threshold) < 0;
                    break;
                case LE:
                    ret = balance.compareTo(threshold) <= 0;
                    break;
                case EQ:
                    ret = balance.compareTo(threshold) == 0;
                    break;
                case NEQ:
                    ret = balance.compareTo(threshold) != 0;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported comparison operation: " + comparisonOperation);
            }
            if (!ret) {
                System.out.println("[Warning] Wallet balance condition unreached!");
            }

            return ret;

        } catch (Exception e) {
            throw new RuntimeException("Error checking balance condition: " + e.getMessage());
        }
    }

    private static Date parseToDate(String targetTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        // dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // 确保为 UTC 时间
        return dateFormat.parse(targetTime);
    }

    public static boolean timeCheckAPI(Word time1, Word time2, Type timeCondition) {
        Web3j web3j = Web3jBuilder.buildWeb3j();

        try {
            // 获取最新区块
            EthBlock latestBlock = web3j.ethGetBlockByNumber(
                    org.web3j.protocol.core.DefaultBlockParameterName.LATEST, false
            ).send();

            // 获取区块时间戳 (以秒为单位的 Unix 时间戳)
            long blockTimestamp = latestBlock.getBlock().getTimestamp().longValue();

            // 将时间戳转换为人类可读格式（可选调试用）
            Date blockDate = new Date(blockTimestamp * 1000L); // 转换为毫秒
            System.out.println("Ethereum block time: " + blockDate);
            // System.out.println("Settled time: " + parseToDate(time1.getContent()));

            if (timeCondition.equals(Type.AFTER)) {
                assert (time1.getType().equals(Type.TIME));
                String targetTime1 = time1.getContent();
                if (blockDate.after(parseToDate(targetTime1))) {
                    return true;
                } else {
                    System.out.println("[Warning] Time condition unreached!");
                    return false;
                }
            } else if (timeCondition.equals(Type.BEFORE)) {
                assert (time1.getType().equals(Type.TIME));
                String targetTime1 = time1.getContent();
                if (blockDate.before(parseToDate(targetTime1))) {
                    return true;
                } else {
                    System.out.println("[Warning] Time condition unreached!");
                    return false;
                }
            } else if (timeCondition.equals(Type.DURING)) {
                assert (time1.getType().equals(Type.TIME));
                assert (time2.getType().equals(Type.TIME));
                String targetTime1 = time1.getContent();
                String targetTime2 = time2.getContent();
                if (blockDate.after(parseToDate(targetTime1)) && blockDate.before(parseToDate(targetTime2))) {
                    return true;
                } else {
                    System.out.println("[Warning] Time condition unreached!");
                    return false;
                }
            } else {
                throw new Exception("Unsupported time operation: " + timeCondition);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error checking Ethereum time: " + e.getMessage());
        }
    }

    public static boolean assetPriceCheckAPI(Word asset, Type comparisonOperation, Word number) {
        double price = 0.0;
        if (asset.getContent().equals("USDT")) {
            price = 1.0;
        } else {
            String apiUrl = "https://api.binance.com/api/v3/ticker/price?symbol=" + asset.getContent() + "USDT";

            Request request = new Request.Builder()
                    .url(apiUrl)
                    .build();

            OkHttpClient client = Web3jBuilder.buildOkHttpClient();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = Objects.requireNonNull(response.body()).string();
                    // System.out.println(responseBody);

                    JSONObject jsonResponse = new JSONObject(responseBody);
                    price = jsonResponse.getDouble("price");
                } else {
                    throw new IOException("Unexpected response: " + response);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error fetching price from Binance: " + e.getMessage(), e);
            }
        }

        double targetPrice = Double.parseDouble(number.getContent());
        boolean ret = false;
        // nowPrice op targetPrice
        switch (comparisonOperation) {
            case GT:
                ret = price > targetPrice;
                break;
            case GE:
                ret = price >= targetPrice;
                break;
            case LT:
                ret = price < targetPrice;
                break;
            case LE:
                ret = price <= targetPrice;
                break;
            case EQ:
                ret = price == targetPrice;
                break;
            case NEQ:
                ret = price != targetPrice;
                break;
            default:
                throw new IllegalArgumentException("Unsupported comparison operation: " + comparisonOperation);
        }

        if (!ret) {
            System.out.println("[Warning] Token price condition unreached!");
        }

        return ret;
    }

    //--------------------------------------------------- Use contract to check (checking) -------------------------------------------------------//

    public static boolean checkEstimateFee(Transaction transaction, BigDecimal gasLimit, Type comparisonOperation) throws IOException {
        Web3j web3j = Web3jBuilder.buildWeb3j();
        BigDecimal gasUsed = new BigDecimal(web3j.ethEstimateGas(transaction).send().getAmountUsed());
        boolean ret = false;

        switch (comparisonOperation) {
            case GT:
                ret = gasUsed.compareTo(gasLimit) > 0;
                break;
            case GE:
                ret = gasUsed.compareTo(gasLimit) >= 0;
                break;
            case LT:
                ret = gasUsed.compareTo(gasLimit) < 0;
                break;
            case LE:
                ret = gasUsed.compareTo(gasLimit) <= 0;
                break;
            case EQ:
                ret = gasUsed.compareTo(gasLimit) == 0;
                break;
            case NEQ:
                ret = gasUsed.compareTo(gasLimit) != 0;
                break;
            default:
                throw new IllegalArgumentException("Unsupported comparison operation: " + comparisonOperation);
        }

        if (!ret) {
            System.out.println("[Warning] Transaction fee condition unreached!");
        }

        return ret;
    }

    public static boolean checkSlippage(Node.SwapStatement swapStatement, String routerAddress,
                                        Word userSlippage, Type comparisonOperation) throws Exception {
        Node.Wallet wallet = swapStatement.getWallet();
        Word assetIn = swapStatement.getAmount().getAsset();
        Word assetOut = swapStatement.getAsset();
        Word platform = swapStatement.getPlatform();

        assert (assetIn.getType().equals(Type.ASSET));
        assert (assetOut.getType().equals(Type.ASSET));
        assert (userSlippage.getType().equals(Type.DEC_FLOAT));

        Web3j web3j = Web3jBuilder.buildWeb3j();

        String inputTokenAddress = Token.getContractAddressByToken(assetIn);
        String outputTokenAddress = Token.getContractAddressByToken(assetOut);

        String walletAddress = wallet.getKey().getContent();
        BigDecimal inputAmountNum = Calculator.calBinaryExp(swapStatement.getAmount().getBinaryExpression());
        BigDecimal inputAmountInMin = inputAmountNum.multiply(new BigDecimal(Token.getTokenDecimals(inputTokenAddress)));

        BigInteger inputAmountInMinToken;
        try {
            inputAmountInMinToken = new BigInteger(inputAmountInMin.toString());
        } catch (Exception e) {
            throw new Exception("Unexpected accuracy, token amount: " + inputAmountNum);
        }

        BigDecimal userSetSlippage = new BigDecimal(userSlippage.getContent());

        // 1. 获取预期的输出数量
        Function getAmountsOutFunction = new Function(
                "getAmountsOut",  // Uniswap 或其他路由器合约的函数名
                Arrays.asList(
                        new Uint256(inputAmountInMinToken),  // 输入的代币数量（最小单位）
                        new Address(inputTokenAddress), new Address(outputTokenAddress)),
                Arrays.asList(new TypeReference<Uint256>() {
                })
        );

        String encodedFunction = FunctionEncoder.encode(getAmountsOutFunction);
        EthCall response = web3j.ethCall(
                Transaction.createEthCallTransaction(
                        walletAddress,
                        routerAddress,
                        encodedFunction
                ), DefaultBlockParameterName.LATEST).send();

        // 获取预期的输出数量（单位：输出代币的最小单位）
        BigInteger expectedOutput = new BigInteger(response.getValue().substring(2), 16);

        BigInteger actualOutput = getSwapActualOutput(walletAddress, inputTokenAddress, outputTokenAddress,
                inputAmountInMinToken, routerAddress, platform.getContent());

        BigDecimal realSlippage = ((new BigDecimal(expectedOutput)).subtract(new BigDecimal(actualOutput))).divide(new BigDecimal(expectedOutput));

        boolean ret = false;

        switch (comparisonOperation) {
            case GT:
                ret = userSetSlippage.compareTo(realSlippage) > 0;
                break;
            case GE:
                ret = userSetSlippage.compareTo(realSlippage) >= 0;
                break;
            case LT:
                ret = userSetSlippage.compareTo(realSlippage) < 0;
                break;
            case LE:
                ret = userSetSlippage.compareTo(realSlippage) <= 0;
                break;
            case EQ:
                ret = userSetSlippage.compareTo(realSlippage) == 0;
                break;
            case NEQ:
                ret = userSetSlippage.compareTo(realSlippage) != 0;
                break;
            default:
                throw new IllegalArgumentException("Unsupported comparison operation: " + comparisonOperation);
        }

        if (!ret) {
            System.out.println("[Warning] Transaction slippage condition unreached!");
        }

        return ret;
    }

    // 通过模拟交易获取实际的输出数量（这里可以根据实际情况进行实现）
    public static BigInteger getSwapActualOutput(String walletAddress, String inputTokenAddress, String outputTokenAddress,
                                                 BigInteger inputAmount, String routerAddress, String platform) throws Exception {
        Web3j web3j = Web3jBuilder.buildWeb3j();
        switch (platform) {
            case "UNISWAP":
                return getUniswapSwapOutput(inputTokenAddress, outputTokenAddress, inputAmount, routerAddress, web3j, walletAddress);
            case "SUSHISWAP":
                return getSushiSwapOutput(inputTokenAddress, outputTokenAddress, inputAmount, routerAddress, web3j, walletAddress);
            case "CURVE":
                // return getCurveSwapOutput(inputTokenAddress, outputTokenAddress, inputAmount, routerAddress, web3j);
            case "ONEINCH":
                // return getOneInchSwapOutput(inputTokenAddress, outputTokenAddress, inputAmount, routerAddress, web3j, walletAddress);
                // 其他平台目前不支持或实现
            default:
                throw new UnsupportedOperationException("Swap is not supported for platform: " + platform);
        }
    }

    private static BigInteger getUniswapSwapOutput(String inputTokenAddress, String outputTokenAddress,
                                                   BigInteger inputAmount, String routerAddress,
                                                   Web3j web3j, String walletAddress) throws Exception {

//        Bytes32[] _path = new Bytes32[2];
//        _path[0] = Calculator.stringToBytes32(inputTokenAddress);
//        _path[1] = Calculator.stringToBytes32(outputTokenAddress);
//        DynamicArray<Bytes32> path = new DynamicArray<Bytes32>(Bytes32.class, _path);


        StaticArray2<org.web3j.abi.datatypes.Type> path = new StaticArray2<>(
                new Address(inputTokenAddress),
                new Address(outputTokenAddress)
        );

        Function swapSimulationFunction = new Function(
                "swapExactTokensForTokens", // Uniswap V2/V3
                Arrays.asList(
                        new Uint256(inputAmount),
                        new Uint256(BigInteger.ZERO), // 最小输出
                        path,
                        new Address(walletAddress),
                        new Uint256(BigInteger.valueOf(System.currentTimeMillis() + 600000)) // 交易超时时间
                ),
                Collections.singletonList(new TypeReference<Uint256>() {
                })
        );

        String encodedFunction = FunctionEncoder.encode(swapSimulationFunction);
        EthCall response = web3j.ethCall(
                Transaction.createEthCallTransaction(walletAddress, routerAddress, encodedFunction),
                DefaultBlockParameterName.LATEST
        ).send();

        if (response.hasError()) {
            throw new Exception("Uniswap swap simulation failed: " + response.getError().getMessage());
        }

        // 解析返回值，假设是 [实际输出量]
        String value = response.getValue();
        List<org.web3j.abi.datatypes.Type> decoded = FunctionReturnDecoder.decode(value,
                Collections.singletonList(new TypeReference<org.web3j.abi.datatypes.Type>() {
                }));
        return ((Uint256) decoded.get(0)).getValue();
    }

    private static BigInteger getSushiSwapOutput(String inputTokenAddress, String outputTokenAddress,
                                                 BigInteger inputAmount, String routerAddress,
                                                 Web3j web3j, String walletAddress) throws Exception {
        // SushiSwap 使用与 Uniswap V2 类似的接口
        return getUniswapSwapOutput(inputTokenAddress, outputTokenAddress, inputAmount, routerAddress, web3j, walletAddress);
    }

    private static BigInteger getCurveSwapOutput(String inputTokenAddress, String outputTokenAddress,
                                                 BigInteger inputAmount, String routerAddress,
                                                 Web3j web3j) throws Exception {
        Function getDyFunction = new Function(
                "get_dy",
                Arrays.asList(
                        new Address(inputTokenAddress), // 输入代币地址
                        new Address(outputTokenAddress), // 输出代币地址
                        new Uint256(inputAmount) // 输入数量
                ),
                Collections.singletonList(new TypeReference<Uint256>() {
                })
        );

        String encodedFunction = FunctionEncoder.encode(getDyFunction);
        EthCall response = web3j.ethCall(
                Transaction.createEthCallTransaction(routerAddress, routerAddress, encodedFunction),
                DefaultBlockParameterName.LATEST
        ).send();

        if (response.hasError()) {
            throw new Exception("Curve swap simulation failed: " + response.getError().getMessage());
        }

        String value = response.getValue();
        List<org.web3j.abi.datatypes.Type> decoded = FunctionReturnDecoder.decode(value,
                Collections.singletonList(new TypeReference<org.web3j.abi.datatypes.Type>() {
                }));
        return ((Uint256) decoded.get(0)).getValue();
    }

    private static BigInteger getOneInchSwapOutput(String inputTokenAddress, String outputTokenAddress,
                                                   BigInteger inputAmount, String routerAddress,
                                                   Web3j web3j, String walletAddress) throws Exception {
        Function oneInchSimulationFunction = new Function(
                "swap", // 1inch 的 swap 方法
                Arrays.asList(
                        new Address(inputTokenAddress),
                        new Address(outputTokenAddress),
                        new Uint256(inputAmount),
                        new Uint256(BigInteger.ZERO), // 最小输出量
                        new Uint256(BigInteger.valueOf(System.currentTimeMillis() + 600000)) // 交易有效期
                ),
                Collections.singletonList(new TypeReference<Uint256>() {
                })
        );

        String encodedFunction = FunctionEncoder.encode(oneInchSimulationFunction);
        EthCall response = web3j.ethCall(
                Transaction.createEthCallTransaction(walletAddress, routerAddress, encodedFunction),
                DefaultBlockParameterName.LATEST
        ).send();

        if (response.hasError()) {
            throw new Exception("1inch swap simulation failed: " + response.getError().getMessage());
        }

        // 假设返回值为 [实际输出量]
        String value = response.getValue();
        List<org.web3j.abi.datatypes.Type> decoded = FunctionReturnDecoder.decode(value,
                Collections.singletonList(new TypeReference<org.web3j.abi.datatypes.Type>() {
                }));
        return ((Uint256) decoded.get(0)).getValue();
    }


}
