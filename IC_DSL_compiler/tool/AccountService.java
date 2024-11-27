package tool;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;

import java.math.BigInteger;

public class AccountService {

    public static BigInteger getNonce(String owner) throws Exception {
        Web3j web3j = Web3jBuilder.buildWeb3j();

        try {
            return web3j.ethGetTransactionCount(
                    owner, DefaultBlockParameterName.LATEST
            ).send().getTransactionCount();
        } catch (Exception e) {
            throw new Exception("Failed to fetch nonce for owner: " + owner, e);
        }
    }

    public static BigInteger getGasPrice() throws Exception {
        Web3j web3j = Web3jBuilder.buildWeb3j();

        try {
            EthGasPrice gasPriceResponse = web3j.ethGasPrice().send();
            return gasPriceResponse.getGasPrice();
        } catch (Exception e) {
            throw new Exception("Failed to fetch gas price from Ethereum network", e);
        }
    }


}
