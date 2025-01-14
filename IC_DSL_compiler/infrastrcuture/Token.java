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
import settings.Settings;

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

        if (Settings.TEST_MODE) {
            switch (tokenName) {
                case "ETH":
                    return "0x0000000000000000000000000000000000000000"; //
                case "USDT":
                    return "0xFB122130C4d28860dbC050A8e024A71a558eB0C1"; //
                case "USDC":
                    return "0x94a9D9AC8a22534E3FaCa9F4e7F2E2cf85d5E4C8"; //
                case "DAI":
                    return "0xFF34B3d4Aee8ddCd6F9AFFFB6Fe49bD371b8a357"; //
                case "BTC":
                    System.out.println("Sorry, this compiler only supports tokens which can be transferred on Etherum network (ERC20).");
                    System.out.println("WBTC but not BTC is supported by our compiler.");
                    return null;
                case "WBTC":
                    return "0x547d4E5748457859a2F0Eef1129eaD5D4A2bB55B"; //
                case "WETH":
                    return "0xfFf9976782d46CC05630D1f6eBAb18b2324d6B14"; //
                case "UNI":
                    return "0xC1a4aD3AaC062d875A9395B041ef5E4885BB8F9c"; //
                case "SUSHI":
                    return null;
                case "AAVE":
                    return null;
                case "MATIC":
                    return null;
                case "COMP":
                    return null;
                default:
                    System.out.println("Unsupported token: " + tokenName);
                    return null;
            }
        } else {
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
                case "WETH":
                    return "0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2";
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

    public static BigInteger getTokenDecimals(String tokenAddress) throws Exception {
        if (tokenAddress.equals("0x0000000000000000000000000000000000000000") ||
                tokenAddress.equals("0xc02aaa39b223fe8d0a0e5c4f27ead9083c756cc2")) {
            return BigInteger.valueOf(10).pow(18);  // ETH
        }

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
                        "0x0000000000000000000000000000000000000000",      // Sender address (or any address, since we're just calling a view function)
                        tokenAddress,                                           // Token contract address
                        encodedFunction
                ), DefaultBlockParameterName.LATEST).send();

        if (response.getError() != null) {
            System.out.println("Error Code: " + response.getError().getCode());
            System.out.println("Error Message: " + response.getError().getMessage());
            System.out.println("Error Data: " + response.getError().getData());
        }

        Uint decimals = (Uint) FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters()).get(0);
        return BigInteger.valueOf(10).pow(decimals.getValue().intValue());
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
