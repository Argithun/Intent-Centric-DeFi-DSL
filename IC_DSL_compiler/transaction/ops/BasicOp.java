package transaction.ops;

import infrastrcuture.QueryService;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.core.methods.request.Transaction;

import java.math.BigInteger;

public class BasicOp {
    protected static RawTransaction constructRawTransaction(
            String from, BigInteger gasPrice, BigInteger gasLimit,
            String to, BigInteger value, String data) throws Exception {
        if (data == null) {
            return RawTransaction.createEtherTransaction(
                    QueryService.getNonce(from),
                    gasPrice,
                    gasLimit,
                    to,
                    value
            );
        } else {
            return RawTransaction.createTransaction(
                    QueryService.getNonce(from),
                    gasPrice,
                    gasLimit,
                    to,
                    value,
                    data
            );
        }
    }

    protected static RawTransaction constructRawTransaction(
            String from, BigInteger gasPrice, BigInteger gasLimit,
            String to, BigInteger value, String data, BigInteger nonceAdd) throws Exception {
        if (data == null) {
            return RawTransaction.createEtherTransaction(
                    QueryService.getNonce(from).add(nonceAdd),
                    gasPrice,
                    gasLimit,
                    to,
                    value
            );
        } else {
            return RawTransaction.createTransaction(
                    QueryService.getNonce(from).add(nonceAdd),
                    gasPrice,
                    gasLimit,
                    to,
                    value,
                    data
            );
        }
    }

    protected static Transaction constructFuncCallTransaction(
            String from, BigInteger gasPrice, BigInteger gasLimit, String to, String data) {
        return Transaction.createFunctionCallTransaction(
                from,
                null,
                gasPrice,
                gasLimit,
                to,
                data
        );
    }
}
