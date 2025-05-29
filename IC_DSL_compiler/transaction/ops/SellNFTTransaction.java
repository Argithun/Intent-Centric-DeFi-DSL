package transaction.ops;

import ast.Node;
import ast.Word;
import infrastrcuture.QueryService;
import infrastrcuture.Token;
import infrastrcuture.Web3jBuilder;
import okhttp3.*;
import settings.ContractAddress;
import settings.Settings;
import tool.PrivateKeyManager;
import tool.Signature;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.UUID;

public class SellNFTTransaction extends BasicOp {

    public static boolean genSellNFTTransaction(Node.Statement statement, String privateKey) throws Exception {
        // https://docs.opensea.io/reference/post_listing
        Node.SellNFTStatement sellNFTStatement = (Node.SellNFTStatement) statement;

        String NFTTokenId = sellNFTStatement.getNFTTokenID();
        String NFTCollectionID = sellNFTStatement.getNFTCollectionID().getContent();
        String fromWalletAddress = sellNFTStatement.getFromWallet().getKey().getContent();
        ArrayList<String> sellStrategies = sellNFTStatement.getStrategy();

        ArrayList<Double> NFTInfo = QueryService.getNFTCollectionInfoByAddress(NFTCollectionID);
        if (NFTInfo == null) {
            return false;
        }
        Double lowestPrice = NFTInfo.get(0);
        Double avgPrice = NFTInfo.get(1);
        Double preferPrice = avgPrice; // ETH

        if (sellStrategies.contains("time-saving")) {
            preferPrice = avgPrice - (avgPrice - lowestPrice) / 10;
        }

        if (sellStrategies.contains("profitable")) {
            if (sellStrategies.contains("time-saving")) {
                preferPrice = avgPrice;
            } else {
                preferPrice = avgPrice + (avgPrice - lowestPrice) / 5;
            }
        }

        BigDecimal preferPriceAccount = new BigDecimal(preferPrice).multiply(
                new BigDecimal(Token.getTokenDecimals(
                        Token.getContractAddressByToken(new Word("ETH", ast.Type.ASSET)))));
        BigInteger preferPriceWei = preferPriceAccount.toBigInteger();

        BigInteger counter = QueryService.getCounter(fromWalletAddress);

        OkHttpClient client = Web3jBuilder.buildOkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");

        String protocol_data_parameters = String.format(
                "{"
                        + "    \"orderType\":0,"
                        + "    \"offer\":["
                        + "      {"
                        + "        \"itemType\":2," // ERC721 (NFT)
                        + "        \"token\":\"%s\","
                        + "        \"identifierOrCriteria\":%s,"
                        + "        \"startAmount\":1,"
                        + "        \"endAmount\":1"
                        + "      }"
                        + "    ],"
                        + "    \"consideration\":["
                        + "      {"
                        + "        \"itemType\":0," // ERC20 (prefer ETH)
                        + "        \"token\":\"0x0000000000000000000000000000000000000000\","
                        + "        \"identifierOrCriteria\":0,"
                        + "        \"startAmount\":%s,"
                        + "        \"endAmount\":%s,"
                        + "        \"recipient\":\"%s\""
                        + "      }"
                        + "    ],"
                        + "    \"startTime\":%s,"
                        + "    \"endTime\":%s,"
                        + "    \"zone\":\"0x0000000000000000000000000000000000000000\","
                        + "    \"zoneHash\":\"0x0000000000000000000000000000000000000000000000000000000000000000\","
                        + "    \"salt\":\"%s\","
                        + "    \"conduitKey\":\"0x0000000000000000000000000000000000000000000000000000000000000000\","
                        + "    \"totalOriginalConsiderationItems\":1,"
                        + "    \"counter\":\"%s\""
                        + "  }",
                NFTCollectionID,
                NFTTokenId,
                preferPriceWei,
                preferPriceWei,
                fromWalletAddress,
                System.currentTimeMillis() / 1000, // 当前时间（Unix 时间戳）
                (System.currentTimeMillis() / 1000) + 86400, // 截止时间：24小时后
                UUID.randomUUID(),
                counter
        );

        String signature = Signature.signPrefixedMessage(protocol_data_parameters, privateKey);

        String requestBody = String.format(
                "{"
                        + "\"protocol_data\":{"
                        + "  \"parameters\":" + protocol_data_parameters
                        + ",  \"signature\":\"%s\""
                        + "},"
                        + "\"protocol_address\":\"%s\""
                        + "}",
                signature,
                ContractAddress.OPENSEA_NFT_EXCHANGE
        );

        RequestBody body = RequestBody.create(mediaType, requestBody);
        Request request = new Request.Builder()
                .url("https://api.opensea.io/api/v2/orders/ethereum/seaport/listings")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("x-api-key", Settings.OPENSEA_API_KEY)
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            System.out.println("NFT list (for sale) created successfully: " + response.body().string());
        } else {
            System.out.println("Failed to list NFT: " + response.body().string());
            return false;
        }
        return true;
    }

}
