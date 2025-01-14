import ast.AstBuilder;
import ast.Node;
import infrastrcuture.QueryService;
import infrastrcuture.Token;
import tool.Signature;
import transaction.TransSubmitter;

import java.math.BigDecimal;
import java.util.ArrayList;


public class Compiler {
    public static void main(String[] args) throws Exception {
        Node intentAst = AstBuilder.buildAst("main.ic");
        TransSubmitter.submitTransactions(intentAst);

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
