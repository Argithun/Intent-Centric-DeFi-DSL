package infrastrcuture;

import ast.Type;
import ast.Word;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;


public class Token {

    public static String getContractAddressByToken(Word token) {
        assert (token.getType() == Type.ASSET);

        String tokenName = token.getContent();

//        assert (!tokenName.equals("ETH"));

        switch (tokenName) {
            case "ETH":
                return "0x0000000000000000000000000000000000000000";
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

    public static BigInteger getTokenDecimals(String tokenAddress) throws Exception {
        Web3j web3j = Web3jBuilder.buildWeb3j();

        // 构造获取 decimals() 方法的 Function
        Function function = new Function(
                "decimals",
                Collections.emptyList(),
                Arrays.asList(new TypeReference<Uint8>() {
                })
        );

        String encodedFunction = FunctionEncoder.encode(function);

        EthCall response = web3j.ethCall(
                Transaction.createEthCallTransaction(
                        "0x0000000000000000000000000000000000000000",     // Sender address (or any address, since we're just calling a view function)
                        tokenAddress,               // Token contract address
                        encodedFunction
                ), DefaultBlockParameterName.LATEST).send();

        if (response.getError() != null) {
            System.out.println("Error Code: " + response.getError().getCode());
            System.out.println("Error Message: " + response.getError().getMessage());
            System.out.println("Error Data: " + response.getError().getData());
        }

        Uint decimals = (Uint) FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters()).get(0);
        return decimals.getValue();
    }

    // 计算代币汇率
    public static double calculateExchangeRate(Word from, Word to) {
        double fromPrice = getTokenNumEqualOneUSDT(from);
        double toPrice = getTokenNumEqualOneUSDT(to);
        return toPrice / fromPrice;
    }

    private static double getTokenNumEqualOneUSDT(Word asset) {
        if (asset.getContent().equals("USDT")) {
            return 1.0;
        } else {
            String apiUrl = "https://api.binance.com/api/v3/ticker/price?symbol=" + asset.getContent() + "USDT";

            Request request = new Request.Builder()
                    .url(apiUrl)
                    .build();

            OkHttpClient client = Web3jBuilder.buildOkHttpClient();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = Objects.requireNonNull(response.body()).string();
                    // System.out.println(responseBody);

                    JSONObject jsonResponse = new JSONObject(responseBody);
                    return jsonResponse.getDouble("price");
                } else {
                    throw new IOException("Unexpected response: " + response);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error fetching price from Binance: " + e.getMessage(), e);
            }
        }
    }
}
