package transaction.ops;

import ast.Node;
import infrastrcuture.QueryService;
import infrastrcuture.Token;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.core.methods.request.Transaction;
import settings.ContractAddress;
import settings.Settings;
import tool.Calculator;
import transaction.TransGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class RepayTransaction extends BasicOp {


    // Compound token address
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

    public static boolean genRepayTransaction(Node.Statement statement, TransGenerator transGenerator) throws Exception {
        Node.RepayBorrowStatement repayBorrowStatement = (Node.RepayBorrowStatement) statement;

        String repayTokenAddress = Token.getContractAddressByToken(repayBorrowStatement.getAmount().getAsset());
        BigDecimal repayTokenNum = Calculator.calBinaryExp(repayBorrowStatement.getAmount().getBinaryExpression());
        BigInteger repayTokenAmount = repayTokenNum.multiply(
                new BigDecimal(Token.getTokenDecimals(repayTokenAddress))).toBigInteger();
        String fromWallet = repayBorrowStatement.getWallet().getKey().getContent();
        String platform = repayBorrowStatement.getPlatform().getContent();

        if (repayBorrowStatement.getAmount().getAsset().getContent().equals("ETH")) {
            System.out.println("[NOTICE] Please transfer ETH to WETH first and then repay WETH.");
            return false;
        }

        if (!platform.equals("Aave") && !platform.equals("Compound")) {
            System.out.println("Unsupported platform for borrowing: " + platform);
            return false;
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
                            new Uint256(1),             // Interest rate mode (1 = Variable)
                            new Address(fromWallet)           // 偿还的用户地址
                    ),
                    Collections.emptyList()
            );
            String repayData = FunctionEncoder.encode(repayFunction);

            RawTransaction rawTransaction = constructRawTransaction(fromWallet, gasPrice, gasLimit, lendingPoolAddress, BigInteger.ZERO, repayData);
            Transaction transaction = constructFuncCallTransaction(fromWallet, gasPrice, gasLimit, lendingPoolAddress, repayData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
        } else {
            String tokenName = repayBorrowStatement.getAmount().getAsset().getContent();
            String repayCtokenAddress = tokenToCtokenAddress(tokenName);
            if (repayCtokenAddress == null) {
                System.out.println(tokenName + "is not supported to be repayed to Compound");
                return false;
            }

            // COMPOUND: 调用 cToken 的 Approve 方法进行代币使用授权
            Function approve = new Function(
                    "approve",
                    Arrays.asList(
                            new Address(repayCtokenAddress),
                            new Uint256(repayTokenAmount)
                    ),
                    Collections.emptyList()
            );
            String approveData = FunctionEncoder.encode(approve);
            RawTransaction preRawTransaction = constructRawTransaction(fromWallet, gasPrice, gasLimit, repayTokenAddress, BigInteger.ZERO, approveData);
            transGenerator.setPreRawTransaction(preRawTransaction);

            // COMPOUND: 调用 cToken 的 repayBorrow 方法进行偿还
            Function repayBorrowFunction = new Function(
                    "repayBorrow",
                    Collections.emptyList(),
                    Collections.emptyList()
            );
            String repayData = FunctionEncoder.encode(repayBorrowFunction);

            RawTransaction rawTransaction = constructRawTransaction(fromWallet, gasPrice, gasLimit, repayCtokenAddress, BigInteger.ZERO, repayData, BigInteger.ONE);
            Transaction transaction = constructFuncCallTransaction(fromWallet, gasPrice, gasLimit, repayCtokenAddress, repayData);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
        }

        return true;
    }

}
