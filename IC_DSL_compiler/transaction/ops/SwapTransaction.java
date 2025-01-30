package transaction.ops;

import ast.Node;
import ast.Word;
import infrastrcuture.QueryService;
import infrastrcuture.Token;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Int128;
import org.web3j.abi.datatypes.generated.Uint160;
import org.web3j.abi.datatypes.generated.Uint24;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.core.methods.request.Transaction;
import settings.ContractAddress;
import settings.Settings;
import tool.Calculator;
import transaction.TransGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SwapTransaction extends BasicOp {

    private static void transETHtoWETH(String walletAddress, BigInteger ETHAmount, TransGenerator transGenerator) throws Exception {
        String WETH_ADDRESS = Token.getContractAddressByToken(new Word("WETH", ast.Type.ASSET));

        Function depositFunction = new Function(
                "deposit",
                Collections.emptyList(),  // deposit 不需要任何参数
                Collections.emptyList()
        );

        String depositData = FunctionEncoder.encode(depositFunction);
        BigInteger gasPrice = QueryService.getGasPrice();
        BigInteger gasLimit = Settings.DEFAULT_GAS_LIMIT;

        RawTransaction rawTransaction = constructRawTransaction(walletAddress, gasPrice, gasLimit,
                WETH_ADDRESS, ETHAmount, depositData);
        Transaction transaction = constructFuncCallTransaction(walletAddress, gasPrice, gasLimit,
                WETH_ADDRESS, depositData);
        transGenerator.setRawTransaction(rawTransaction);
        transGenerator.setTransaction(transaction);
    }

    private static void transWETHtoETH(String walletAddress, BigInteger WETHAmount, TransGenerator transGenerator) throws Exception {
        String WETH_ADDRESS = Token.getContractAddressByToken(new Word("WETH", ast.Type.ASSET));

        Function withdrawFunction = new Function(
                "withdraw",
                Arrays.asList(new Uint256(WETHAmount)),  // 传入要转换的 WETH 数量
                Collections.emptyList()
        );

        String withdrawData = FunctionEncoder.encode(withdrawFunction);
        BigInteger gasPrice = QueryService.getGasPrice();
        BigInteger gasLimit = Settings.DEFAULT_GAS_LIMIT;

        RawTransaction rawTransaction = constructRawTransaction(walletAddress, gasPrice, gasLimit,
                WETH_ADDRESS, BigInteger.ZERO, withdrawData);
        Transaction transaction = constructFuncCallTransaction(walletAddress, gasPrice, gasLimit,
                WETH_ADDRESS, withdrawData);
        transGenerator.setRawTransaction(rawTransaction);
        transGenerator.setTransaction(transaction);
    }

    public static class ExactInputSingleParams extends StaticStruct {
        public Address tokenIn;
        public Address tokenOut;
        public Uint24 fee;
        public Address recipient;
        public Uint256 deadline;
        public Uint256 amountIn;
        public Uint256 amountOutMinimum;
        public Uint160 sqrtPriceLimitX96;

        public ExactInputSingleParams(Address tokenIn, Address tokenOut, Uint24 fee, Address recipient, Uint256 deadline,
                                      Uint256 amountIn, Uint256 amountOutMinimum, Uint160 sqrtPriceLimitX96) {
            super(tokenIn, tokenOut, fee, recipient, deadline, amountIn, amountOutMinimum, sqrtPriceLimitX96);
            this.tokenIn = tokenIn;
            this.tokenOut = tokenOut;
            this.fee = fee;
            this.recipient = recipient;
            this.deadline = deadline;
            this.amountIn = amountIn;
            this.amountOutMinimum = amountOutMinimum;
            this.sqrtPriceLimitX96 = sqrtPriceLimitX96;
        }
    }

    public static class OneInchSwapParams extends StaticStruct {
        public Address srcToken;
        public Address dstToken;
        public Address srcReceiver;
        public Address dstReceiver;
        public Uint256 amount;
        public Uint256 minReturnAMount;
        public Uint256 flags;
        public DynamicBytes permit;

        public OneInchSwapParams(Address srcToken, Address dstToken, Address srcReceiver, Address dstReceiver,
                                 Uint256 amount, Uint256 minReturnAMount, Uint256 flags, DynamicBytes permit) {
            super(srcToken, dstToken, srcReceiver, dstReceiver, amount, minReturnAMount, flags, permit);
            this.srcToken = srcToken;
            this.dstToken = dstToken;
            this.srcReceiver = srcReceiver;
            this.dstReceiver = dstReceiver;
            this.amount = amount;
            this.minReturnAMount = minReturnAMount;
            this.flags = flags;
            this.permit = permit;
        }

    }


    public static boolean genSwapTransaction(Node.Statement statement, TransGenerator transGenerator) throws Exception {
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
            return false;
        }

        if (swapStatement.getAmount().getAsset().getContent().equals("ETH")) {
            if (swapStatement.getAsset().getContent().equals("WETH")) {
                transETHtoWETH(wallet, inTokenAmount, transGenerator);
                return true;
            } else {
                System.out.println("[NOTICE] Please transfer ETH to WETH first and then swap WETH for other tokens");
                return false;
            }
        }


        if (swapStatement.getAsset().getContent().equals("ETH")) {
            if (swapStatement.getAmount().getAsset().getContent().equals("WETH")) {
                transWETHtoETH(wallet, inTokenAmount, transGenerator);
                return true;
            } else {
                System.out.println("[NOTICE] Please transfer token to WETH first and then swap WETH for ETH");
                return false;
            }
        }

        BigInteger gasPrice = QueryService.getGasPrice();
        BigInteger gasLimit = Settings.DEFAULT_GAS_LIMIT;

        if (platform.equals("Uniswap")) {
            // UNISWAP 使用 exactInputSingle 方法
            String routerAddress = Settings.TEST_MODE ? ContractAddress.UNISWAP_ROUTER_V3_Sepolia : ContractAddress.UNISWAP_ROUTER_V3;
            transGenerator.setRouterAddress(routerAddress);

            RawTransaction preRawTransaction = Token.approveToken(wallet, routerAddress, inTokenAddress,
                    inTokenAmount, gasPrice, gasLimit);
            transGenerator.setPreRawTransaction_1(preRawTransaction);

            ExactInputSingleParams params = new ExactInputSingleParams(
                    new Address(inTokenAddress),
                    new Address(outTokenAddress),
                    new Uint24(3000),
                    new Address(wallet),
                    new Uint256(System.currentTimeMillis() / 1000 + 600),
                    new Uint256(inTokenAmount),
                    new Uint256(0),
                    new Uint160(0)
            );

            Function swapFunction = new Function(
                    "exactInputSingle",
                    Arrays.asList(params),
                    Collections.emptyList()
            );

            String swapData = FunctionEncoder.encode(swapFunction);

//            System.out.println(swapData);
//            Signature.keccak256Hash("exactInputSingle((address,address,uint24,address,uint256,uint256,uint256,uint160))");

            RawTransaction rawTransaction = constructRawTransaction(wallet, gasPrice, gasLimit,
                    routerAddress, BigInteger.ZERO, swapData);
            Transaction transaction = constructFuncCallTransaction(wallet, gasPrice, gasLimit,
                    routerAddress, swapData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
        } else if (platform.equals("Sushiswap")) {
            // Sushiswap: 使用 swapExactTokensForTokens 方法
            String routerAddress = ContractAddress.SUSHISWAP_ROUTER;
            transGenerator.setRouterAddress(routerAddress);

            RawTransaction preRawTransaction = Token.approveToken(wallet, routerAddress, inTokenAddress,
                    inTokenAmount, gasPrice, gasLimit);
            transGenerator.setPreRawTransaction_1(preRawTransaction);

            List<Type> swapParameters = Arrays.asList(
                    new Uint256(inTokenAmount),
                    new Uint256(BigInteger.ZERO),
                    new DynamicArray<Address>(new Address(inTokenAddress), new Address(outTokenAddress)),
                    new Address(wallet),
                    new Uint256(System.currentTimeMillis() / 1000 + 600)
            );

            Function swapFunction = new Function(
                    "swapExactTokensForTokens",
                    swapParameters,
                    Collections.emptyList()
            );

            String swapData = FunctionEncoder.encode(swapFunction);

            RawTransaction rawTransaction = constructRawTransaction(wallet, gasPrice, gasLimit,
                    routerAddress, BigInteger.ZERO, swapData);
            Transaction transaction = constructFuncCallTransaction(wallet, gasPrice, gasLimit,
                    routerAddress, swapData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
        } else if (platform.equals("Curve")) {
            // CURVE: 使用 exchange 方法
            String curvePoolAddress = ContractAddress.CURVE_3POOL; // Curve 3pool 合约地址
            String routerAddress = curvePoolAddress;
            transGenerator.setRouterAddress(routerAddress);

            RawTransaction preRawTransaction = Token.approveToken(wallet, routerAddress, inTokenAddress,
                    inTokenAmount, gasPrice, gasLimit);
            transGenerator.setPreRawTransaction_1(preRawTransaction);

            ArrayList<String> coins = new ArrayList<>();
            coins.add("DAI");
            coins.add("USDC");
            coins.add("USDT");

            int inTokenIndex = coins.indexOf(swapStatement.getAmount().getAsset().getContent()); // 代币索引（需要根据具体池的配置确认）
            int outTokenIndex = coins.indexOf(swapStatement.getAsset().getContent()); // 目标代币索引
            if (inTokenIndex < 0 || outTokenIndex < 0) {
                System.out.println("Sorry, Curve on this platform only support DAI/USDC/USDT");
                return false;
            }

            Function exchangeFunction = new Function(
                    "exchange",
                    Arrays.asList(
                            new Int128(inTokenIndex),        // 输入代币索引
                            new Int128(outTokenIndex),       // 输出代币索引
                            new Uint256(inTokenAmount),       // 输入代币数量
                            new Uint256(0)              // 最小输出代币数量
                    ),
                    Collections.emptyList()
            );
            String exchangeData = FunctionEncoder.encode(exchangeFunction);

            RawTransaction rawTransaction = constructRawTransaction(wallet, gasPrice, gasLimit,
                    curvePoolAddress, BigInteger.ZERO, exchangeData);
            Transaction transaction = constructFuncCallTransaction(wallet, gasPrice, gasLimit,
                    curvePoolAddress, exchangeData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
        } else {
            // 1INCH: 使用 API 获取交易路径，然后调用合约执行
            String oneInchRouterAddress = ContractAddress.ONEINCH_ROUTER; // 1inch Router 地址
            String routerAddress = oneInchRouterAddress;
            transGenerator.setRouterAddress(routerAddress);

            RawTransaction preRawTransaction = Token.approveToken(wallet, routerAddress, inTokenAddress,
                    inTokenAmount, gasPrice, gasLimit);
            transGenerator.setPreRawTransaction_1(preRawTransaction);

            OneInchSwapParams oneInchSwapParams = new OneInchSwapParams(
                    new Address(inTokenAddress),
                    new Address(outTokenAddress),
                    new Address(wallet),
                    new Address(wallet),
                    new Uint256(inTokenAmount),
                    new Uint256(BigInteger.ZERO),
                    new Uint256(BigInteger.ZERO),
                    new DynamicBytes(new byte[0])
            );

            // 调用 swap 方法
            Function oneInchSwapFunction = new Function(
                    "swap",
                    Arrays.asList(
                            new Address(wallet),
                            oneInchSwapParams,
                            new DynamicBytes(new byte[0])
                    ),
                    Collections.emptyList()
            );
            String oneInchSwapData = FunctionEncoder.encode(oneInchSwapFunction);

            RawTransaction rawTransaction = constructRawTransaction(wallet, gasPrice, gasLimit,
                    oneInchRouterAddress, BigInteger.ZERO, oneInchSwapData);
            Transaction transaction = constructFuncCallTransaction(wallet, gasPrice, gasLimit,
                    oneInchRouterAddress, oneInchSwapData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
        }

        return true;
    }

}
