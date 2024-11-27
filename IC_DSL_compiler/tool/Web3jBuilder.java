package tool;

import okhttp3.OkHttpClient;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import settings.Settings;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class Web3jBuilder {
    public static Web3j buildWeb3j() {
        String infuraUrl = "https://mainnet.infura.io/v3/" + Settings.INFURA_API_KEY;

        if (Settings.IF_USE_PROXY) {
            OkHttpClient okHttpClient = buildOkHttpClient();
            HttpService httpService = new HttpService(infuraUrl, okHttpClient);
            return Web3j.build(httpService);
        } else {
            HttpService httpService = new HttpService(infuraUrl);
            return Web3j.build(httpService);
        }
    }

    public static OkHttpClient buildOkHttpClient() {
        if (Settings.IF_USE_PROXY) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Settings.PROXY_HOST_NAME, Settings.PROXY_PORT));

            return new OkHttpClient.Builder()
                    .proxy(proxy)
                    .build();
        } else {
            return new OkHttpClient();
        }
    }


}
