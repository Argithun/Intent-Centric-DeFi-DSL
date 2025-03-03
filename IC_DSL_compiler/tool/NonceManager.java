package tool;

import ast.Node;
import infrastrcuture.QueryService;

import java.math.BigInteger;
import java.util.HashMap;

public class NonceManager {
    private static HashMap<String, BigInteger> accountToNonce = new HashMap<>();

    public static void initAccountToNonce(Node root) throws Exception {
        for (Node.TriggerStatement triggerStatement : root.getTriggerStatements()) {
            if (triggerStatement.getStatement() instanceof Node.AccountStatement) {
                continue;
            }
            Node.Statement statement = triggerStatement.getStatement();

            if (statement instanceof Node.TransferStatement) {
                Node.TransferStatement transferStatement = (Node.TransferStatement) statement;
                setNonceOfAccount(transferStatement.getFromWallet().getKey().getContent());
                setNonceOfAccount(transferStatement.getToWallet().getKey().getContent());
            } else if (statement instanceof Node.BorrowStatement) {
                Node.BorrowStatement borrowStatement = (Node.BorrowStatement) statement;
                setNonceOfAccount(borrowStatement.getForWallet().getKey().getContent());
            } else if (statement instanceof Node.RepayBorrowStatement) {
                Node.RepayBorrowStatement repayBorrowStatement = (Node.RepayBorrowStatement) statement;
                setNonceOfAccount(repayBorrowStatement.getWallet().getKey().getContent());
            } else if (statement instanceof Node.SwapStatement) {
                Node.SwapStatement swapStatement = (Node.SwapStatement) statement;
                setNonceOfAccount(swapStatement.getWallet().getKey().getContent());
            } else if (statement instanceof Node.AddLiquidityStatement) {
                Node.AddLiquidityStatement addLiquidityStatement = (Node.AddLiquidityStatement) statement;
                setNonceOfAccount(addLiquidityStatement.getWallets().get(0).getKey().getContent());
            } else if (statement instanceof Node.RemoveLiquidityStatement) {
                Node.RemoveLiquidityStatement removeLiquidityStatement = (Node.RemoveLiquidityStatement) statement;
                setNonceOfAccount(removeLiquidityStatement.getWallets().get(0).getKey().getContent());
            } else if (statement instanceof Node.StakeStatement) {
                Node.StakeStatement stakeStatement = (Node.StakeStatement) statement;
                setNonceOfAccount(stakeStatement.getWallet().getKey().getContent());
            } else if (statement instanceof Node.SellNFTStatement) {
                Node.SellNFTStatement sellNFTStatement = (Node.SellNFTStatement) statement;
                setNonceOfAccount(sellNFTStatement.getFromWallet().getKey().getContent());
            } else if (statement instanceof Node.BuyNFTStatement) {
                Node.BuyNFTStatement buyNFTStatement = (Node.BuyNFTStatement) statement;
                setNonceOfAccount(buyNFTStatement.getBudgetWallet().getKey().getContent());
            }
        }
    }

    private static void setNonceOfAccount(String walletAddress) throws Exception {
        if (accountToNonce.get(walletAddress) != null) {
            return;
        }
        accountToNonce.put(walletAddress, QueryService.getNonce(walletAddress));
    }

    public static BigInteger getNonceByAccount(String walletAddress) {
        BigInteger nonce = accountToNonce.get(walletAddress);
        if (nonce == null) {
            System.out.println("[Fatal Error] Invalid account address!");
            return null;
        }
        accountToNonce.put(walletAddress, nonce.add(BigInteger.ONE));
        return nonce;
    }


}
