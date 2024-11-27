package transaction;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.FunctionEncoder;


public class Transaction {

    public interface GeneralTransaction {
    }

    // 原生以太坊交易
    public static class EthereumTransaction implements GeneralTransaction {
        private String from;         // 发送方地址
        private String to;           // 接收方地址
        private BigInteger value;    // 转账金额（单位：Wei） 非原生 ETH 该字段置为 0
        private BigInteger gasLimit; // 最大 gas 消耗
        private BigInteger gasPrice; // 每单位 gas 的价格
        private BigInteger nonce;    // 交易的 nonce
        private String data;         // 交易数据（例如合约调用时的编码数据）

        // 构造函数
        public EthereumTransaction(String from, String to, BigInteger value, BigInteger gasLimit,
                                   BigInteger gasPrice, BigInteger nonce, String data) {
            this.from = from;
            this.to = to;
            this.value = value;
            this.gasLimit = gasLimit;
            this.gasPrice = gasPrice;
            this.nonce = nonce;
            this.data = data;
        }

        // Getters 和 Setters
        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public BigInteger getValue() {
            return value;
        }

        public void setValue(BigInteger value) {
            this.value = value;
        }

        public BigInteger getGasLimit() {
            return gasLimit;
        }

        public void setGasLimit(BigInteger gasLimit) {
            this.gasLimit = gasLimit;
        }

        public BigInteger getGasPrice() {
            return gasPrice;
        }

        public void setGasPrice(BigInteger gasPrice) {
            this.gasPrice = gasPrice;
        }

        public BigInteger getNonce() {
            return nonce;
        }

        public void setNonce(BigInteger nonce) {
            this.nonce = nonce;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "EthereumTransaction{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", value=" + value +
                    ", gasLimit=" + gasLimit +
                    ", gasPrice=" + gasPrice +
                    ", nonce=" + nonce +
                    ", data='" + data + '\'' +
                    '}';
        }
    }

    // 合约交易
    public static class TokenTransaction extends EthereumTransaction {
        private String contractAddress; // 代币合约地址
        private String methodName;      // 调用的方法名（如 transfer, approve）
        private List<Type> parameters; // 方法参数（接收者地址、金额等）
        private List<TypeReference<?>> outputList; // 函数返回参数列表


        public TokenTransaction(BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit,
                                String contractAddress, String methodName, List<Type> parameters,
                                List<TypeReference<?>> outputList) {
            super(null, null, BigInteger.ZERO, gasLimit, gasPrice, nonce, null);
            this.contractAddress = contractAddress;
            this.methodName = methodName;
            this.parameters = parameters;
            this.outputList = outputList;

            Function function = new Function(
                    methodName,
                    parameters,
                    outputList
            );

            this.setData(FunctionEncoder.encode(function));
        }

        // 编码交易数据
        public String encodeFuncData(List<TypeReference<?>> outputList) {
            Function function = new Function(
                    methodName,
                    parameters,
                    outputList
            );
            return FunctionEncoder.encode(function);
        }

        public String getContractAddress() {
            return contractAddress;
        }

        public void setContractAddress(String contractAddress) {
            this.contractAddress = contractAddress;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public List<Type> getParameters() {
            return parameters;
        }

        public void setParameters(List<Type> parameters) {
            this.parameters = parameters;
        }

        public List<TypeReference<?>> getOutputList() {
            return outputList;
        }

        public void setOutputList(List<TypeReference<?>> outputList) {
            this.outputList = outputList;
        }
    }


}
