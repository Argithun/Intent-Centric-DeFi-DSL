import ast.AstBuilder;
import ast.Node;
import optimize.dependency.DependencyAnalysis;
import optimize.dependency.DependencyGraph;
import settings.Settings;
import tool.NonceManager;
import tool.PrivateKeyManager;
import transaction.TransSubmitter;
import transaction.TransSubmitterParallel;

import java.io.File;


public class Compiler {
    public static void main(String[] args) throws Exception {
        System.out.println("Current working directory: " + new File(".").getAbsolutePath());
//        Node intentAst = AstBuilder.buildAst("graph.ic");
        Node intentAst = AstBuilder.buildAst("main.ic");
        PrivateKeyManager.setPrivateKey(intentAst);
        NonceManager.initAccountToNonce(intentAst);
        DependencyGraph dependencyGraph = DependencyAnalysis.genDependencyGraph(intentAst);

//        if (Settings.PARALLEL_MODE) {
//            TransSubmitterParallel.submitTransactionsParallel(dependencyGraph);
//        } else {
//            TransSubmitter.submitTransactions(intentAst, dependencyGraph);
//        }

// ------------------------------------- for test ---------------------------------- //

//        Web3j web3j = Web3jBuilder.buildWeb3j();
//        EthGetBalance ethGetBalance = web3j.ethGetBalance("0xd98eC7068456b34628744c6496a985B9b75D7086", DefaultBlockParameterName.LATEST).send();
//        System.out.println(ethGetBalance.getBalance());

//        Web3j web3j = Web3jBuilder.buildWeb3j();
//        ContractFuncService erc20 = new ContractFuncService(
//                "0x94a9D9AC8a22534E3FaCa9F4e7F2E2cf85d5E4C8",
//                web3j,
//                new ReadonlyTransactionManager(web3j, "0xd98eC7068456b34628744c6496a985B9b75D7086"),
//                new DefaultGasProvider()
//        );
//        System.out.println(erc20.balanceOf("0xd98eC7068456b34628744c6496a985B9b75D7086").send());
//        System.out.println(Token.getTokenDecimals("0x94a9D9AC8a22534E3FaCa9F4e7F2E2cf85d5E4C8"));

//        Signature.keccak256Hash("decreaseLiquidity((uint256,uint128,uint256,uint256,uint256))");
//        Signature.keccak256Hash("removeLiquidity(address,address,uint256,uint256,uint256,address,uint256)");
//        Signature.keccak256Hash("addLiquidity(address,address,uint256,uint256,uint256,uint256,address,uint256)");
//        Signature.keccak256Hash("mint((address,address,uint24,int24,int24,uint256,uint256,uint256,uint256,address,uint256))");
//        Signature.keccak256Hash("borrow(address,uint256,uint256,uint16,address)");
//        Signature.keccak256Hash("repayBorrow()");
//        Signature.keccak256Hash("approve(address,uint256)");
//        Signature.keccak256Hash("transfer(address,uint256)");
//        Signature.keccak256Hash("mint(uint256)");
//        Signature.keccak256Hash("withdraw(uint256)");
//        Signature.keccak256Hash("swap(address,(address,address,address,address,uint256,uint256,uint256,bytes),bytes)");

//        System.out.println(Token.getTokenDecimals("0x6B175474E89094C44Da98b954EedeAC495271d0F"));

//        ArrayList<String> ret = QueryService.selectNFTCollection(new ArrayList<>());
//        System.out.println(ret.get(0));
//        System.out.println(ret.get(1));
//        System.out.println(ret.get(2));
//        System.out.println(ret.get(3));

//        System.out.println(QueryService.getOpenseaSlug("ethereum", "0x1a17531d136a3a4e0ef05575867fc7c59dae3069"));

//        System.out.println(QueryService.getCounter("0xd98eC7068456b34628744c6496a985B9b75D7086"));

//        System.out.println(QueryService.getNFTCollectionInfoByAddress("0x1a17531d136a3a4e0ef05575867fc7c59dae3069"));
    }


}
