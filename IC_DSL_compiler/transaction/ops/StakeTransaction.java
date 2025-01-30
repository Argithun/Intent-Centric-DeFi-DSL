package transaction.ops;

import ast.Node;
import infrastrcuture.QueryService;
import infrastrcuture.Token;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint16;
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

public class StakeTransaction extends BasicOp {

    private static String tokenToCtokenAddress(String tokenName) {
        switch (tokenName) {
            case "ETH":
                return "0x4Ddc2D193948926D02f9B1fE9e1daa0718270ED5";
            case "DAI":
                return "0x5d3a536E4D6DbD6114cc1Ead35777bAB948E3643";
            case "USDT":
                return "0xf650C3d88D12dB855b8bf7D11Be6C55A4e07dCC9";
            case "USDC":
                return "0x39AA39c021dfbaE8faC545936693aC917d5E7563";
            case "WBTC":
                return "0xC11b1268C1A384e55C48c2391d8d480264A3A7F4";
            case "UNI":
                return "0x35A18000230DA775CAc24873d00Ff85BccdeD550";
            case "SUSHI":
                return "0x4B0181102A0112A2ef11AbEE5563bb4a3176c9d7";
            case "AAVE":
                return "0xe65cdB6479BaC1e22340E4E755fAE7E509EcD06c";
            case "COMP":
                return "0x70e36f6BF80a52b3B46b3aF8e106CC0ed743E8e4";
        }
        return null;
    }

    public static boolean genStakeTransaction(Node.Statement statement, TransGenerator transGenerator) throws Exception {
        Node.StakeStatement stakeStatement = (Node.StakeStatement) statement;

        String stakeTokenAddress = Token.getContractAddressByToken(stakeStatement.getAmount().getAsset());

        if (stakeStatement.getAmount().getAsset().getContent().equals("ETH")) {
            System.out.println("[NOTICE] Please transfer ETH to WETH first and then stake WETH.");
            return false;
        }

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

            RawTransaction preRawTransaction = Token.approveToken(stakeWallet, lendingPoolAddress,
                    stakeTokenAddress, stakeTokenAmount, gasPrice, gasLimit);
            transGenerator.setPreRawTransaction_1(preRawTransaction);

            Function depositFunction = new Function(
                    "deposit",
                    Arrays.asList(
                            new Address(stakeTokenAddress),   // 要质押的资产地址
                            new Uint256(stakeTokenAmount),    // 要质押的资产数量
                            new Address(stakeWallet),         // 用户地址
                            new Uint16(0)               // Referral code (0)
                    ),
                    Collections.emptyList()
            );
            String depositData = FunctionEncoder.encode(depositFunction);

            RawTransaction rawTransaction = constructRawTransaction(stakeWallet, gasPrice, gasLimit,
                    lendingPoolAddress, BigInteger.ZERO, depositData);
            Transaction transaction = constructFuncCallTransaction(stakeWallet, gasPrice, gasLimit,
                    lendingPoolAddress, depositData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
        } else if (strategy.contains("middle-risk")) {
            if (!stakeStatement.getAmount().getAsset().getContent().equals("DAI")) {
                System.out.println("Only DAI staking is supported by MakerDAO");
                return false;
            }

            // MakerDAO: 调用 DSR（Dai Savings Rate）合约的 join 方法进行质押
            String dsrContractAddress = ContractAddress.MAKERDAO_DSR; // 替换为实际 DSR 合约地址

            RawTransaction preRawTransaction = Token.approveToken(stakeWallet, dsrContractAddress,
                    stakeTokenAddress, stakeTokenAmount, gasPrice, gasLimit);
            transGenerator.setPreRawTransaction_1(preRawTransaction);

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

            RawTransaction rawTransaction = constructRawTransaction(stakeWallet, gasPrice, gasLimit,
                    dsrContractAddress, BigInteger.ZERO, joinData);
            Transaction transaction = constructFuncCallTransaction(stakeWallet, gasPrice, gasLimit,
                    dsrContractAddress, joinData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
        } else if (strategy.contains("high-risk")) {
            // COMPOUND: 调用 cToken 的 mint 方法进行质押
            String tokenName = stakeStatement.getAmount().getAsset().getContent();
            String cTokenAddress = tokenToCtokenAddress(tokenName);
            if (cTokenAddress == null) {
                System.out.println(tokenName + " staking is not supported by Compound.");
                return false;
            }

            // 步骤 1: 调用原始资产的 approve 方法，授权 cToken 合约提取资产
            RawTransaction preRawTransaction = Token.approveToken(stakeWallet, cTokenAddress,
                    stakeTokenAddress, stakeTokenAmount, gasPrice, gasLimit);
            transGenerator.setPreRawTransaction_1(preRawTransaction);

            // 步骤 2: 调用 cToken 合约的 mint 方法进行质押
            Function mintFunction = new Function(
                    "mint",
                    Arrays.asList(
                            new Uint256(stakeTokenAmount)
                    ), // mint 方法无参数
                    Collections.emptyList()
            );
            String mintData = FunctionEncoder.encode(mintFunction);

            RawTransaction rawTransaction = constructRawTransaction(stakeWallet, gasPrice, gasLimit,
                    cTokenAddress, BigInteger.ZERO, mintData);
            Transaction transaction = constructFuncCallTransaction(stakeWallet, gasPrice, gasLimit,
                    cTokenAddress, mintData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
        } else {
            // AAVE: 调用 LendingPool 的 deposit 方法进行质押
            String lendingPoolAddress = ContractAddress.AAVE_LENDING_POOL;  // AAVE LendingPool 地址（Mainnet）

            RawTransaction preRawTransaction = Token.approveToken(stakeWallet, lendingPoolAddress,
                    stakeTokenAddress, stakeTokenAmount, gasPrice, gasLimit);
            transGenerator.setPreRawTransaction_1(preRawTransaction);

            Function depositFunction = new Function(
                    "deposit",
                    Arrays.asList(
                            new Address(stakeTokenAddress),   // 要质押的资产地址
                            new Uint256(stakeTokenAmount),    // 要质押的资产数量
                            new Address(stakeWallet),         // 用户地址
                            new Uint16(0)               // Referral code (0)
                    ),
                    Collections.emptyList()
            );
            String depositData = FunctionEncoder.encode(depositFunction);

            RawTransaction rawTransaction = constructRawTransaction(stakeWallet, gasPrice, gasLimit,
                    lendingPoolAddress, BigInteger.ZERO, depositData);
            Transaction transaction = constructFuncCallTransaction(stakeWallet, gasPrice, gasLimit,
                    lendingPoolAddress, depositData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
        }
        return true;
    }

}
