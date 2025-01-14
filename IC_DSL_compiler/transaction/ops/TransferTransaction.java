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
import settings.Settings;
import tool.Calculator;
import transaction.TransGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class TransferTransaction extends BasicOp {

    public static boolean genTransferTransaction(Node.Statement statement, TransGenerator transGenerator) throws Exception {
        Node.TransferStatement transferStatement = (Node.TransferStatement) statement;

        String fromWallet = transferStatement.getFromWallet().getKey().getContent();
        String toWallet = transferStatement.getToWallet().getKey().getContent();

        String tokenAddress = Token.getContractAddressByToken(transferStatement.getAmount().getAsset());
        BigDecimal tokenNum = Calculator.calBinaryExp(transferStatement.getAmount().getBinaryExpression());

        // Convert token amount to the smallest unit
        BigInteger tokenAmount = tokenNum.multiply(new BigDecimal(Token.getTokenDecimals(tokenAddress))).toBigInteger();

        BigInteger gasPrice = QueryService.getGasPrice();
        BigInteger gasLimit = Settings.DEFAULT_GAS_LIMIT;

        Function function = new Function(
                "transfer",
                Arrays.asList(new Address(toWallet), new Uint256(tokenAmount)),
                Collections.emptyList());
        String encodedFunction = FunctionEncoder.encode(function);

        if (transferStatement.getAmount().getAsset().getContent().equals("ETH")) {
            RawTransaction rawTransaction = constructRawTransaction(fromWallet, gasPrice, gasLimit, toWallet, tokenAmount, null);
            Transaction transaction = constructFuncCallTransaction(fromWallet, gasPrice, gasLimit, toWallet, encodedFunction);
            transGenerator.setRawTransaction(rawTransaction);
            transGenerator.setTransaction(transaction);
            return true;
        }

        RawTransaction rawTransaction = constructRawTransaction(fromWallet, gasPrice, gasLimit, tokenAddress, BigInteger.ZERO, encodedFunction);
        Transaction transaction = constructFuncCallTransaction(fromWallet, gasPrice, gasLimit, tokenAddress, encodedFunction);
        transGenerator.setRawTransaction(rawTransaction);
        transGenerator.setTransaction(transaction);

        return true;
    }

}
