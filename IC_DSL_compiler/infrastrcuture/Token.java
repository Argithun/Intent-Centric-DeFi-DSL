package infrastrcuture;

import ast.Type;
import ast.Word;


public class Token {

    public static String getContractAddressByToken(Word token) {
        assert (token.getType() == Type.ASSET);

        String tokenName = token.getContent();

        assert (!tokenName.equals("ETH"));

        switch (tokenName) {
            case "USDT":
                return "0xdAC17F958D2ee523a2206206994597C13D831ec7";
            case "USDC":
                return "0xA0b86991c6218b36c1d19D4a2e9Eb0cE3606eB48";
            case "DAI":
                return "0x6B175474E89094C44Da98b954EedeAC495271d0F";
            case "BTC":
                System.out.println("Sorry, this compiler only supports tokens which can be transferred on ETH network (ERC20).");
                System.out.println("WBTC but not BTC is supported by our compiler.");
                return null;
            case "WBTC":
                return "0x2260FAC5E5542a773Aa44fBCfeDf7C193bc2C599";
            case "UNI":
                return "0x1f9840a85d5aF5bf1D1762F925BDADdC4201F984";
            case "SUSHI":
                return "0x6B3595068778DD592e39A122f4f5a5cF09C90fE2";
            case "AAVE":
                return "0x7Fc66500c84A76Ad7e9c93437bFc5Ac33E2DDaE9";
            case "MATIC":
                return "0x7D1AfA7B718fb893dB30A3aBc0Cfc608AaCfeBB0";
            case "COMP":
                return "0xc00e94Cb662C3520282E6f5717214004A7f26888";
            default:
                System.out.println("Unsupported token: " + tokenName);
                return null;
        }
    }


}
