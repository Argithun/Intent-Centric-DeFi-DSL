package transaction.ops;

import ast.Node;
import infrastrcuture.QueryService;
import infrastrcuture.Token;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.generated.Uint128;
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

public class RemoveLiquidityTransaction extends BasicOp {

    public static class DecreaseLiquidityParams extends StaticStruct {
        public Uint256 tokenId;
        public Uint128 liquidity;
        public Uint256 amount0Min;
        public Uint256 amount1Min;
        public Uint256 deadline;

        public DecreaseLiquidityParams(Uint256 tokenId, Uint128 liquidity, Uint256 amount0Min,
                                       Uint256 amount1Min, Uint256 deadline) {
            super(tokenId, liquidity, amount0Min, amount1Min, deadline);
            this.tokenId = tokenId;
            this.liquidity = liquidity;
            this.amount0Min = amount0Min;
            this.amount1Min = amount1Min;
            this.deadline = deadline;
        }

    }

    public static boolean genRemoveLiquidityTransaction(Node.Statement statement, TransGenerator transGenerator) throws Exception {
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
        BigInteger gasPrice = QueryService.getGasPrice();
        BigInteger gasLimit = Settings.DEFAULT_GAS_LIMIT;

        // 默认取第一个钱包作为交易发起者
        String fromWallet = wallets.get(0);
        String token0 = outTokenAddresses.get(0);  // 第一个代币地址
        String token1 = outTokenAddresses.get(1);  // 第二个代币地址
        BigInteger amount0 = outTokenAmounts.get(0); // 第一个代币的数量
        BigInteger amount1 = outTokenAmounts.get(1); // 第二个代币的数量
        String tokenId = removeLiquidityStatement.getTokenId();
        String liquidityNum = removeLiquidityStatement.getLiquidityNum();
        BigInteger deadline = BigInteger.valueOf(System.currentTimeMillis() / 1000 + 1800);

        if (platform.equals("Uniswap")) {
            if (tokenId == null) {
                System.out.println("You need to provide a liquidity NFT id to remove Liquidity on Uniswap.");
                return false;
            }

            String contractAddress = ContractAddress.UNISWAP_V3_POSITION_NFT;

            DecreaseLiquidityParams decreaseLiquidityParams = new DecreaseLiquidityParams(
                    new Uint256(new BigInteger(tokenId, 16)),
                    new Uint128(new BigInteger(liquidityNum)),
                    new Uint256(amount0.multiply(BigInteger.valueOf(9)).divide(BigInteger.valueOf(10))),
                    new Uint256(amount1.multiply(BigInteger.valueOf(9)).divide(BigInteger.valueOf(10))),
                    new Uint256(deadline)
            );

            Function decreaseLiquidityFunction = new Function(
                    "decreaseLiquidity",
                    Arrays.asList(decreaseLiquidityParams),
                    Arrays.asList(new TypeReference<Uint256>() {
                    }, new TypeReference<Uint256>() {
                    })
            );

            String encodedData = FunctionEncoder.encode(decreaseLiquidityFunction);

            RawTransaction rawTransaction = constructRawTransaction(fromWallet, gasPrice, gasLimit, contractAddress, BigInteger.ZERO, encodedData);
            Transaction transaction = constructFuncCallTransaction(fromWallet, gasPrice, gasLimit, contractAddress, encodedData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
            return true;
        } else if (platform.equals("Sushiswap")) {
            if (tokenId != null) {
                System.out.println("You don't need to provide a liquidity NFT id to remove Liquidity on Sushiswap.");
            }

            String contractAddress = ContractAddress.SUSHISWAP_ROUTER;

            // 构建 removeLiquidity 的函数调用
            Function removeLiquidityFunction = new Function(
                    "removeLiquidity",
                    Arrays.asList(
                            new Address(token0),
                            new Address(token1),
                            new Uint256(new BigInteger(liquidityNum)),
                            new Uint256(amount0.multiply(BigInteger.valueOf(9)).divide(BigInteger.valueOf(10))),
                            new Uint256(amount1.multiply(BigInteger.valueOf(9)).divide(BigInteger.valueOf(10))),
                            new Address(fromWallet),
                            new Uint256(deadline)
                    ),
                    Collections.emptyList()
            );

            String removeLiquidityData = FunctionEncoder.encode(removeLiquidityFunction);

            RawTransaction rawTransaction = constructRawTransaction(fromWallet, gasPrice, gasLimit, contractAddress, BigInteger.ZERO, removeLiquidityData);
            Transaction transaction = constructFuncCallTransaction(fromWallet, gasPrice, gasLimit, contractAddress, removeLiquidityData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
            return true;
        } else {
            System.out.println("Unexpected error for platform: " + platform);
            return false;
        }

    }

}
