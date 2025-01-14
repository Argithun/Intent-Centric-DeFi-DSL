package transaction;

import ast.Node;
import ast.Word;
import infrastrcuture.QueryService;
import infrastrcuture.Token;
import infrastrcuture.Web3jBuilder;
import okhttp3.*;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.StaticArray2;
import org.web3j.abi.datatypes.generated.Uint160;
import org.web3j.abi.datatypes.generated.Uint24;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import settings.Settings;
import settings.ContractAddress;
import tool.Calculator;
import tool.Signature;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import static transaction.ops.AddLiquidityTransaction.genAddLiquidityTransaction;
import static transaction.ops.BorrowTransaction.genBorrowTransaction;
import static transaction.ops.BuyNFTTransaction.genBuyNFTTransaction;
import static transaction.ops.RemoveLiquidityTransaction.genRemoveLiquidityTransaction;
import static transaction.ops.RepayTransaction.genRepayTransaction;
import static transaction.ops.SellNFTTransaction.genSellNFTTransaction;
import static transaction.ops.StakeTransaction.genStakeTransaction;
import static transaction.ops.SwapTransaction.genSwapTransaction;
import static transaction.ops.TransferTransaction.genTransferTransaction;


public class TransGenerator {
    private final Node.Statement statement;
    private RawTransaction preRawTransaction;
    private RawTransaction rawTransaction;
    private Transaction transaction;
    private String routerAddress;
    private boolean constructSuccess;

    public TransGenerator(Node.Statement statement) {
        this.statement = statement;
        this.preRawTransaction = null;
        this.rawTransaction = null;
        this.transaction = null;
        this.routerAddress = null;
        this.constructSuccess = true;
    }

    public RawTransaction getPreRawTransaction() {
        return preRawTransaction;
    }

    public RawTransaction getRawTransaction() {
        return rawTransaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getRouterAddress() {
        return routerAddress;
    }

    public boolean getConstructSuccess() {
        return constructSuccess;
    }

    public void setPreRawTransaction(RawTransaction preRawTransaction) {
        this.preRawTransaction = preRawTransaction;
    }

    public void setRawTransaction(RawTransaction rawTransaction) {
        this.rawTransaction = rawTransaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setRouterAddress(String routerAddress) {
        this.routerAddress = routerAddress;
    }

    public void genTransaction() throws Exception {
        if (statement instanceof Node.TransferStatement) {
            this.constructSuccess = genTransferTransaction(statement, this);
        } else if (statement instanceof Node.BorrowStatement) {
            this.constructSuccess = genBorrowTransaction(statement, this);
        } else if (statement instanceof Node.RepayBorrowStatement) {
            this.constructSuccess = genRepayTransaction(statement, this);
        } else if (statement instanceof Node.SwapStatement) {
            this.constructSuccess = genSwapTransaction(statement, this);
        } else if (statement instanceof Node.AddLiquidityStatement) {
            this.constructSuccess = genAddLiquidityTransaction(statement, this);
        } else if (statement instanceof Node.RemoveLiquidityStatement) {
            this.constructSuccess = genRemoveLiquidityTransaction(statement, this);
        } else if (statement instanceof Node.StakeStatement) {
            this.constructSuccess = genStakeTransaction(statement, this);
        } else if (statement instanceof Node.SellNFTStatement) {
            this.constructSuccess = genSellNFTTransaction(statement);
        } else if (statement instanceof Node.BuyNFTStatement) {
            this.constructSuccess = genBuyNFTTransaction(statement);
        } else {
            System.out.println("Unsupported type of transaction statement: " + statement);
            this.constructSuccess = false;
        }
    }

}