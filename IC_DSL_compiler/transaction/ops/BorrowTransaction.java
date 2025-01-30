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
import java.util.Arrays;
import java.util.Collections;

public class BorrowTransaction extends BasicOp {

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

    public static boolean genBorrowTransaction(Node.Statement statement, TransGenerator transGenerator) throws Exception {
        Node.BorrowStatement borrowStatement = (Node.BorrowStatement) statement;

        String borrowTokenAddress = Token.getContractAddressByToken(borrowStatement.getBorrowAmount().getAsset());
        BigDecimal borrowTokenNum = Calculator.calBinaryExp(borrowStatement.getBorrowAmount().getBinaryExpression());
        String borrowForWallet = borrowStatement.getForWallet().getKey().getContent();
        String platform = borrowStatement.getPlatform().getContent();

        if (!platform.equals("Aave") && !platform.equals("Compound")) {
            System.out.println("Unsupported platform for borrowing: " + platform);
            return false;
        }

        // Convert borrow and collateral amounts to smallest units
        BigInteger borrowAmount = borrowTokenNum.multiply(
                new BigDecimal(Token.getTokenDecimals(borrowTokenAddress))).toBigInteger();

        BigInteger gasPrice = QueryService.getGasPrice();
        BigInteger gasLimit = Settings.DEFAULT_GAS_LIMIT;

        // Prepare transaction data based on the platform
        if (platform.equals("Aave")) {
            if (borrowStatement.getBorrowAmount().getAsset().getContent().equals("ETH")) {
                System.out.println("[NOTICE] Please borrow WETH first and then transfer to ETH.");
                return false;
            }

            Function borrowFunction = new Function(
                    "borrow",
                    Arrays.asList(
                            new Address(borrowTokenAddress),  // Asset to borrow
                            new Uint256(borrowAmount),        // Borrow amount
                            new Uint256(1),             // Interest rate mode (1 = Variable)
                            new Uint16(0),              // Referral code
                            new Address(borrowForWallet)      // On behalf of
                    ),
                    Collections.emptyList()
            );
            String encodedFunction = FunctionEncoder.encode(borrowFunction);

            RawTransaction rawTransaction = constructRawTransaction(borrowForWallet, gasPrice,
                    gasLimit, ContractAddress.AAVE_LENDING_POOL, BigInteger.ZERO, encodedFunction);
            Transaction transaction = constructFuncCallTransaction(borrowForWallet, gasPrice,
                    gasLimit, ContractAddress.AAVE_LENDING_POOL, encodedFunction);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
        } else {
            String tokenName = borrowStatement.getBorrowAmount().getAsset().getContent();
            borrowTokenAddress = tokenToCtokenAddress(tokenName);
            if (borrowTokenAddress == null) {
                System.out.println(tokenName + "is not supported to be borrowed from Compound");
                return false;
            }

            // COMPOUND Borrow: Data for `borrow` method
            Function borrowFunction = new Function(
                    "borrow",
                    Arrays.asList(new Uint256(borrowAmount)), // Borrow amount
                    Collections.emptyList()
            );
            String encodedFunction = FunctionEncoder.encode(borrowFunction);

            RawTransaction rawTransaction = constructRawTransaction(borrowForWallet, QueryService.getGasPrice(),
                    Settings.DEFAULT_GAS_LIMIT, borrowTokenAddress, BigInteger.ZERO, encodedFunction);
            Transaction transaction = constructFuncCallTransaction(borrowForWallet, QueryService.getGasPrice(),
                    Settings.DEFAULT_GAS_LIMIT, borrowTokenAddress, encodedFunction);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
        }
        return true;
    }

}
