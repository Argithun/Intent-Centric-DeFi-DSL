package tool;

import ast.Type;
import ast.Word;

import infrastrcuture.Token;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;


public class ConditionCheck {
    public static boolean balanceCheckAPI(String walletKey, BigDecimal threshold, Word token, Type comparisonOperation) {
        assert (token.getType().equals(Type.ASSET));

        Web3j web3j = Web3jBuilder.buildWeb3j();

        String tokenName = token.getContent();

        try {
            // 获取 walletKey 对应账户的 tokenName 代币余额，并使用 comparisonOperation 操作符与 threshold 比较
            BigDecimal balance;

            if (tokenName.equals("ETH")) {
                EthGetBalance ethGetBalance = web3j.ethGetBalance(walletKey, DefaultBlockParameterName.LATEST).send();
                balance = new BigDecimal(ethGetBalance.getBalance());  // 将余额从 BigInteger 转为 BigDecimal
            } else {
                String tokenAddress = Token.getContractAddressByToken(token);
                if (tokenAddress == null) {
                    throw new IllegalArgumentException("Unknown token: " + tokenName);
                }

                ERC20Service erc20 = new ERC20Service(
                        tokenAddress,
                        web3j,
                        new ReadonlyTransactionManager(web3j, walletKey),
                        new DefaultGasProvider()
                );

                BigInteger tokenBalance = erc20.balanceOf(walletKey).send();
                balance = new BigDecimal(tokenBalance);  // 将余额从 BigInteger 转为 BigDecimal
            }

            // 以太坊使用18位精度，统一处理
            balance = balance.divide(BigDecimal.TEN.pow(18), 18, RoundingMode.DOWN);  // 除以 10^18，以太坊的标准精度

            // balance op threshold
            switch (comparisonOperation) {
                case GT:
                    return balance.compareTo(threshold) > 0;
                case GE:
                    return balance.compareTo(threshold) >= 0;
                case LT:
                    return balance.compareTo(threshold) < 0;
                case LE:
                    return balance.compareTo(threshold) <= 0;
                case EQ:
                    return balance.compareTo(threshold) == 0;
                case NEQ:
                    return balance.compareTo(threshold) != 0;
                default:
                    throw new IllegalArgumentException("Unsupported comparison operation: " + comparisonOperation);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error checking balance condition: " + e.getMessage());
        }
    }

    private static Date parseToDate(String targetTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // 确保为 UTC 时间

        return dateFormat.parse(targetTime);
    }

    public static boolean timeCheckAPI(Word time1, Word time2, Type timeCondition) {
        Web3j web3j = Web3jBuilder.buildWeb3j();

        try {
            // 获取最新区块
            EthBlock latestBlock = web3j.ethGetBlockByNumber(
                    org.web3j.protocol.core.DefaultBlockParameterName.LATEST, false
            ).send();

            // 获取区块时间戳 (以秒为单位的 Unix 时间戳)
            long blockTimestamp = latestBlock.getBlock().getTimestamp().longValue();

            // 将时间戳转换为人类可读格式（可选调试用）
            Date blockDate = new Date(blockTimestamp * 1000L); // 转换为毫秒
            System.out.println("Ethereum block time: " + blockDate);

            if (timeCondition.equals(Type.AFTER)) {
                assert (time1.getType().equals(Type.TIME));
                String targetTime1 = time1.getContent();
                return blockDate.after(parseToDate(targetTime1));
            } else if (timeCondition.equals(Type.BEFORE)) {
                assert (time1.getType().equals(Type.TIME));
                String targetTime1 = time1.getContent();
                return blockDate.before(parseToDate(targetTime1));
            } else if (timeCondition.equals(Type.DURING)) {
                assert (time1.getType().equals(Type.TIME));
                assert (time2.getType().equals(Type.TIME));
                String targetTime1 = time1.getContent();
                String targetTime2 = time2.getContent();
                return blockDate.after(parseToDate(targetTime1)) && blockDate.before(parseToDate(targetTime2));
            } else {
                throw new Exception("Unsupported time operation: " + timeCondition);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error checking Ethereum time: " + e.getMessage());
        }
    }

    public static boolean assetPriceCheckAPI(Word asset, Type comparisonOperation, Word number) {
        double price = 0.0;
        if (asset.getContent().equals("USDT")) {
            price = 1.0;
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
                    price = jsonResponse.getDouble("price");
                } else {
                    throw new IOException("Unexpected response: " + response);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error fetching price from Binance: " + e.getMessage(), e);
            }
        }

        double targetPrice = Double.parseDouble(number.getContent());

        // nowPrice op targetPrice
        switch (comparisonOperation) {
            case GT:
                return price > targetPrice;
            case GE:
                return price >= targetPrice;
            case LT:
                return price < targetPrice;
            case LE:
                return price <= targetPrice;
            case EQ:
                return price == targetPrice;
            case NEQ:
                return price != targetPrice;
            default:
                throw new IllegalArgumentException("Unsupported comparison operation: " + comparisonOperation);
        }
    }

    //-----------------------------------------------------------------------------------------------------------------//

    



}
