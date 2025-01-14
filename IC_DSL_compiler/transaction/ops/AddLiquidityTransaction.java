package transaction.ops;

import ast.Node;
import infrastrcuture.QueryService;
import infrastrcuture.Token;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int24;
import org.web3j.abi.datatypes.generated.Uint128;
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

public class AddLiquidityTransaction extends BasicOp {

    public static class MintParams extends StaticStruct {
        public Address token0;
        public Address token1;
        public Uint24 fee;
        public Int24 tickLower;
        public Int24 tickUpper;
        public Uint256 amount0Desired;
        public Uint256 amount1Desired;
        public Uint256 amount0Min;
        public Uint256 amount1Min;
        public Address recipient;
        public Uint256 deadline;

        public MintParams(Address token0, Address token1, Uint24 fee, Int24 tickLower, Int24 tickUpper,
                          Uint256 amount0Desired, Uint256 amount1Desired, Uint256 amount0Min,
                          Uint256 amount1Min, Address recipient, Uint256 deadline) {
            super(token0, token1, fee, tickLower, tickUpper, amount0Desired, amount1Desired,
                    amount0Min, amount1Min, recipient, deadline);
            this.token0 = token0;
            this.token1 = token1;
            this.tickLower = tickLower;
            this.tickUpper = tickUpper;
            this.amount0Desired = amount0Desired;
            this.amount1Desired = amount1Desired;
            this.amount0Min = amount0Min;
            this.amount1Min = amount1Min;
            this.recipient = recipient;
            this.deadline = deadline;
        }
    }

    public static boolean genAddLiquidityTransaction(Node.Statement statement, TransGenerator transGenerator) throws Exception {
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

            if (amount.getAsset().getContent().equals("ETH")) {
                System.out.println("[NOTICE] Please transfer ETH to WETH first and then use WETH to add liquidity.");
                return false;
            }

            inTokenAddresses.add(inTokenAddress);
            BigDecimal inTokenNum = Calculator.calBinaryExp(amount.getBinaryExpression());
            inTokenAmounts.add(inTokenNum.multiply(new BigDecimal(Token.getTokenDecimals(inTokenAddress))).toBigInteger());
        }

        BigInteger gasPrice = QueryService.getGasPrice();
        BigInteger gasLimit = Settings.DEFAULT_GAS_LIMIT; // 设定合理的 Gas 限制

        String fromWallet = wallets.get(0);
        String token0 = inTokenAddresses.get(0);  // 第一个代币地址
        String token1 = inTokenAddresses.get(1);  // 第二个代币地址
        BigInteger amount0 = inTokenAmounts.get(0); // 第一个代币的数量
        BigInteger amount1 = inTokenAmounts.get(1); // 第二个代币的数量
        int fee = 3000; // 设置费用级别（0.3%）
        int tickLower = -887220; // 设置价格范围的 lower tick
        int tickUpper = 887220;  // 设置价格范围的 upper tick
        BigInteger amount0Min = amount0.multiply(BigInteger.valueOf(9)).divide(BigInteger.valueOf(10));  // 最小数量（防止滑点）
        BigInteger amount1Min = amount1.multiply(BigInteger.valueOf(9)).divide(BigInteger.valueOf(10));  // 最小数量（防止滑点）
        String recipient = fromWallet; // 接收流动性池位置的地址
        BigInteger deadline = BigInteger.valueOf(System.currentTimeMillis() / 1000 + 1800);  // 设置 30 分钟的截止时间

        if (platform.equals("Uniswap")) {
            String contractAddress = ContractAddress.UNISWAP_V3_POSITION_NFT;

            MintParams mintParams = new MintParams(
                    new Address(token0),
                    new Address(token1),
                    new Uint24(fee),
                    new Int24(tickLower),
                    new Int24(tickUpper),
                    new Uint256(amount0),
                    new Uint256(amount1),
                    new Uint256(amount0Min),
                    new Uint256(amount1Min),
                    new Address(recipient),
                    new Uint256(deadline)
            );

            Function mintFunction = new Function(
                    "mint",
                    Arrays.asList(mintParams),
                    Arrays.asList(new TypeReference<Uint256>() {
                    }, new TypeReference<Uint128>() {
                    }, new TypeReference<Uint256>() {
                    }, new TypeReference<Uint256>() {
                    })
            );
            String encodedData = FunctionEncoder.encode(mintFunction);

            RawTransaction rawTransaction = constructRawTransaction(fromWallet, gasPrice, gasLimit, contractAddress, BigInteger.ZERO, encodedData);
            Transaction transaction = constructFuncCallTransaction(fromWallet, gasPrice, gasLimit, contractAddress, encodedData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
            return true;
        } else if (platform.equals("Sushiswap")) {
            String contractAddress = ContractAddress.SUSHISWAP_ROUTER;

            Function addLiquidityFunction = new Function(
                    "addLiquidity",
                    Arrays.asList(
                            new Address(token0),
                            new Address(token1),
                            new Uint256(amount0),
                            new Uint256(amount1),
                            new Uint256(amount0Min),
                            new Uint256(amount1Min),
                            new Address(recipient),
                            new Uint256(deadline)
                    ),
                    Arrays.asList(
                            new TypeReference<Uint256>() {
                            }, // amountA
                            new TypeReference<Uint256>() {
                            }, // amountB
                            new TypeReference<Uint256>() {
                            }  // liquidity
                    )
            );

            String encodedData = FunctionEncoder.encode(addLiquidityFunction);
            RawTransaction rawTransaction = constructRawTransaction(fromWallet, gasPrice, gasLimit, contractAddress, BigInteger.ZERO, encodedData);
            Transaction transaction = constructFuncCallTransaction(fromWallet, gasPrice, gasLimit, contractAddress, encodedData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
            return true;
        } else {
            System.out.println("Unexpected error for platform: " + platform);
            return false;
        }
    }

}
