package infrastrcuture;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import settings.ContractAddress;
import settings.Settings;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryService {

    public static BigInteger getNonce(String owner) throws Exception {
        Web3j web3j = Web3jBuilder.buildWeb3j();

        try {
            return web3j.ethGetTransactionCount(
                    owner, DefaultBlockParameterName.LATEST
            ).send().getTransactionCount();
        } catch (Exception e) {
            throw new Exception("Failed to fetch nonce for owner: " + owner, e);
        }
    }

    public static BigInteger getGasPrice() throws Exception {
        Web3j web3j = Web3jBuilder.buildWeb3j();

        try {
            EthGasPrice gasPriceResponse = web3j.ethGasPrice().send();
            return gasPriceResponse.getGasPrice();
        } catch (Exception e) {
            throw new Exception("Failed to fetch gas price from Ethereum network", e);
        }
    }

    public static JSONArray getNFTCollectionIDsByTrade() {
        try {
            // API 基础信息
            String baseUrl = "https://restapi.nftscan.com/api";
            String endpoint = "/v2/statistics/ranking/trade";
            String apiKey = Settings.NFTSCAN_API_KEY;

            // 构造请求 URL 和参数
            String time = "1d"; // 时间范围
            String sortField = "volume"; // 排序字段
            String sortDirection = "desc"; // 排序方向
            String queryParams = String.format("?time=%s&sort_field=%s&sort_direction=%s", time, sortField, sortDirection);
            URL url = new URL(baseUrl + endpoint + queryParams);

            // 打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("x-api-key", apiKey);

            // 检查响应码
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 响应码
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                conn.disconnect();

                JSONObject jsonObject = new JSONObject(response.toString());
                return jsonObject.getJSONArray("data");
            } else {
                System.out.println("Query for NFT trade failed：" + responseCode);
                conn.disconnect();
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> selectNFTCollection(ArrayList<String> NFTQualifiers) throws Exception {
        JSONArray NFTs = getNFTCollectionIDsByTrade();

        List<Double> floorPrices = new ArrayList<>();
        List<Integer> itemsTotals = new ArrayList<>();
        List<Double> volumes = new ArrayList<>();
        List<Double> marketCaps = new ArrayList<>();

        assert NFTs != null;
        int n = NFTs.length();
        for (int i = 0; i < n; i++) {
            JSONObject nft = NFTs.getJSONObject(i);

            floorPrices.add(nft.optDouble("floor_price", 0));
            itemsTotals.add(nft.optInt("items_total", 0));
            volumes.add(nft.optDouble("volume", 0));
            marketCaps.add(nft.optDouble("market_cap", 0));
        }

        double floorPriceThreshold = getPercentileThreshold(floorPrices, 25);
        double itemsTotalThreshold = getPercentileThreshold(itemsTotals, 25);
        double volumeThreshold = getPercentileThreshold(volumes, 75);
        double marketCapThreshold = getPercentileThreshold(marketCaps, 75);

        List<JSONObject> filteredNFTs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            JSONObject nft = NFTs.getJSONObject(i);
            boolean matches = true;

            // 根据关键词进行逐步过滤
            if (NFTQualifiers.contains("price-increasing")) {
                double priceChange = nft.optDouble("average_price_change", 0);
                if (priceChange <= 0) {
                    matches = false;
                }
            }

            if (NFTQualifiers.contains("price-decreasing")) {
                double priceChange = nft.optDouble("average_price_change", 0);
                if (priceChange >= 0) {
                    matches = false;
                }
            }

            if (NFTQualifiers.contains("inexpensive")) {
                double floorPrice = nft.optDouble("floor_price", 0);
                if (floorPrice >= floorPriceThreshold) {
                    matches = false;
                }
            }

            if (NFTQualifiers.contains("rare")) {
                int itemsTotal = nft.optInt("items_total", 0);
                if (itemsTotal >= itemsTotalThreshold) {
                    matches = false;
                }
            }

            if (NFTQualifiers.contains("popular")) {
                double volume = nft.optDouble("volume", 0);
                if (volume < volumeThreshold) {
                    matches = false;
                }
            }

            if (NFTQualifiers.contains("mainstream")) {
                double volume = nft.optDouble("volume", 0);
                double marketCap = nft.optDouble("market_cap", 0);
                if (volume < volumeThreshold || marketCap < marketCapThreshold) {
                    matches = false;
                }
            }

            if (matches) {
                filteredNFTs.add(nft);
            }
        }

        if (filteredNFTs.isEmpty()) {
            throw new Exception("No matching NFTs found.");
        } else {
            double minPrice = Double.MAX_VALUE;
            JSONObject selected = filteredNFTs.get(0);
            for (JSONObject jsonObject : filteredNFTs) {
                if (jsonObject.optDouble("average_price") < minPrice) {
                    minPrice = jsonObject.optDouble("average_price");
                    selected = jsonObject;
                }
            }
//            System.out.println(selected.optString("contract_name"));

            ArrayList<String> ret = new ArrayList<>();
            ret.add(selected.optString("contract_address"));
            ret.add(selected.optString("contract_name"));
            ret.add(String.valueOf(selected.optDouble("lowest_price")));
            ret.add(String.valueOf(selected.optDouble("average_price")));
            return ret;
        }
    }

    private static double getPercentileThreshold(List<? extends Number> list, double percentile) {
        List<Double> doubleList = new ArrayList<>();
        for (Number num : list) {
            doubleList.add(num.doubleValue());
        }
        Collections.sort(doubleList);
        int index = (int) (percentile / 100 * doubleList.size());
        return doubleList.get(index);
    }

    public static String getOpenseaSlug(String chain, String contractAddress) throws Exception {
        OkHttpClient client = Web3jBuilder.buildOkHttpClient();
        // 构造 URL
        String url = String.format("https://opensea.io/assets/%s/%s", chain, contractAddress);

        // 创建请求对象
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        // 发起请求
        Response response = client.newCall(request).execute();

        // 检查响应是否成功
        if (!response.isSuccessful()) {
            throw new Exception("Failed to fetch slug: HTTP response code " + response.code());
        }

        // 获取响应体
        String responseBody = response.body().string();

        // 使用正则表达式提取 slug
        Pattern pattern = Pattern.compile("\"https://opensea\\.io/collection/(.*?)\"");
        Matcher matcher = pattern.matcher(responseBody);

        if (matcher.find()) {
            return matcher.group(1); // 返回匹配的 slug
        } else {
            throw new Exception("Failed to extract slug: No matching pattern found");
        }
    }

    public static BigInteger getCounter(String offererAddress) throws Exception {
        Web3j web3j = Web3jBuilder.buildWeb3j();
        ContractFuncService erc20 = new ContractFuncService(
                ContractAddress.SEAPORT_CONTRACT,
                web3j,
                new ReadonlyTransactionManager(web3j, offererAddress),
                new DefaultGasProvider()
        );

        return erc20.getCounter(offererAddress).send();
    }


}
