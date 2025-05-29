package transaction.ops;

import ast.Node;
import ast.Word;
import infrastrcuture.QueryService;
import infrastrcuture.Token;
import infrastrcuture.Web3jBuilder;
import okhttp3.*;
import settings.ContractAddress;
import settings.Settings;
import tool.Calculator;
import tool.PrivateKeyManager;
import tool.Signature;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.UUID;

public class SimpleBuyNFTTransaction {

    public static boolean genSimpleBuyNFTTransaction(Node.Statement statement, String privateKey) throws Exception {
        // https://docs.opensea.io/reference/post_criteria_offer_v2
        // 提取 BuyNFTStatement 中的相关信息
        Node.SimpleBuyNFTStatement buyNFTStatement = (Node.SimpleBuyNFTStatement) statement;

        // 获取预算信息
        String budgetTokenAddress = Token.getContractAddressByToken(buyNFTStatement.getBudgetAmount().getAsset()); // ERC20 合约地址

        if (buyNFTStatement.getBudgetAmount().getAsset().getContent().equals("ETH")) {
            System.out.println("[NOTICE] Please transfer ETH to WETH first and then use WETH to buy NFT.");
            return false;
        }

        String nftTokenId = buyNFTStatement.getNFTTokenID();
        String nftCollectionId = buyNFTStatement.getNFTCollectionID().getContent();
        String nftCollectionSlug = QueryService.getOpenseaSlug("ethereum", nftCollectionId);

        BigDecimal budgetTokenNum = Calculator.calBinaryExp(buyNFTStatement.getBudgetAmount().getBinaryExpression());
        BigInteger tokenDecimals = Token.getTokenDecimals(budgetTokenAddress);
        BigInteger budgetTokenAmount = budgetTokenNum.multiply(new BigDecimal(tokenDecimals)).toBigInteger(); // 支付的代币数量

        String budgetWallet = buyNFTStatement.getBudgetWallet().getKey().getContent();

        // String NFTPlatform = buyNFTStatement.getNFTPlatform().getContent();

        // 获取符合限定条件的 NFT 集合信息
        // List: contract addr, contract name, lowest price (ETH), average price (ETH)

        BigInteger counter = QueryService.getCounter(budgetWallet);

        // 构建 OpenSea Criteria Offer API 的请求体
        OkHttpClient client = Web3jBuilder.buildOkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");

        String protocol_data_parameters = String.format(
                "{"
                        + "    \"orderType\":0,"
                        + "    \"offer\":["
                        + "      {"
                        + "        \"itemType\":1," // ERC20
                        + "        \"token\":\"%s\","
                        + "        \"identifierOrCriteria\":0,"
                        + "        \"startAmount\":%s,"
                        + "        \"endAmount\":%s"
                        + "      }"
                        + "    ],"
                        + "    \"offerer\":\"%s\","
                        + "    \"startTime\":%s,"
                        + "    \"endTime\":%s,"
                        + "    \"zone\":\"0x0000000000000000000000000000000000000000\","
                        + "    \"zoneHash\":\"0x0000000000000000000000000000000000000000000000000000000000000000\","
                        + "    \"salt\":\"%s\","
                        + "    \"conduitKey\":\"0x0000000000000000000000000000000000000000000000000000000000000000\","
                        + "    \"totalOriginalConsiderationItems\":1,"
                        + "    \"counter\":\"%s\""
                        + "  }",
                budgetTokenAddress,
                budgetTokenAmount,
                budgetTokenAmount,
                budgetWallet,
                System.currentTimeMillis() / 1000, // 当前时间（Unix 时间戳）
                (System.currentTimeMillis() / 1000) + 7200, // 2h后到期
                UUID.randomUUID(),
                counter
        );

        String signature = Signature.signPrefixedMessage(protocol_data_parameters, privateKey);

        String requestBody = String.format(
                "{"
                        + "\"protocol_data\":{"
                        + "  \"parameters\":"
                        + protocol_data_parameters
                        + ",  \"signature\":\"%s\""
                        + "},"
                        + "\"criteria\":{"
                        + "  \"collection\":{"
                        + "    \"slug\":\"%s\""
                        + "  },"
                        + "  \"contract\":{"
                        + "    \"address\":\"%s\""
                        + "  },"
                        + "  \"encoded_token_ids\":\"%s\"" // 指定购买的具体 tokenId（如适用）
                        + "},"
                        + "\"protocol_address\":\"%s\""
                        + "}",
                signature,
                nftCollectionSlug,
                nftCollectionId,
                nftTokenId,
                ContractAddress.OPENSEA_NFT_EXCHANGE
        );

        // 创建请求对象
        RequestBody body = RequestBody.create(mediaType, requestBody);
        Request request = new Request.Builder()
                .url("https://api.opensea.io/api/v2/offers")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("x-api-key", Settings.OPENSEA_API_KEY)
                .build();

        // 发送请求并获取响应
        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            System.out.println("NFT purchase offer created successfully: " + response.body().string());
        } else {
            System.out.println("Failed to create NFT purchase offer: " + response.body().string());
            return false;
        }

        return true;
    }


}
