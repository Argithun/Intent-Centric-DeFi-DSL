package transaction;

import ast.Node;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.core.methods.request.Transaction;

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
    private String privateKey;
    private RawTransaction preRawTransaction_1;
    private RawTransaction preRawTransaction_2;
    private RawTransaction rawTransaction;
    private Transaction transaction;
    private String routerAddress;
    private boolean constructSuccess;

    public TransGenerator(Node.Statement statement, String privateKey) {
        this.statement = statement;
        this.privateKey = privateKey;
        this.preRawTransaction_1 = null;
        this.preRawTransaction_2 = null;
        this.rawTransaction = null;
        this.transaction = null;
        this.routerAddress = null;
        this.constructSuccess = true;
    }

    public RawTransaction getPreRawTransaction_1() {
        return preRawTransaction_1;
    }

    public RawTransaction getPreRawTransaction_2() {
        return preRawTransaction_2;
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

    public void setPreRawTransaction_1(RawTransaction preRawTransaction_1) {
        this.preRawTransaction_1 = preRawTransaction_1;
    }

    public void setPreRawTransaction_2(RawTransaction preRawTransaction_2) {
        this.preRawTransaction_2 = preRawTransaction_2;
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
            this.constructSuccess = genSellNFTTransaction(statement, this.privateKey);
        } else if (statement instanceof Node.BuyNFTStatement) {
            this.constructSuccess = genBuyNFTTransaction(statement, this.privateKey);
        } else {
            System.out.println("Unsupported type of transaction statement: " + statement);
            this.constructSuccess = false;
        }
    }

}