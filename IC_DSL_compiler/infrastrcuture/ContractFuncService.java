package infrastrcuture;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Collections;

public class ContractFuncService extends Contract {

    public ContractFuncService(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) {
        super("", contractAddress, web3j, transactionManager, gasProvider);
    }

    // 账户余额
    public RemoteCall<BigInteger> balanceOf(String owner) {
        Function function = new Function(
                "balanceOf",
                Collections.singletonList(new Address(owner)),
                Collections.singletonList(new TypeReference<Uint256>() {
                })
        );
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getCounter(String offerer) {
        Function function = new Function(
                "getCounter",
                Collections.singletonList(new Address(offerer)),
                Collections.singletonList(new TypeReference<Uint256>() {
                })
        );
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

}
