package settings;

import java.math.BigInteger;

public class Settings {
    // TODO replace to your own settings

    // Test on Sepolia
    public static final boolean TEST_MODE = true;

    // Your Infura API Key
    public static final String INFURA_API_KEY = "ddae120beb6046179b8ac42d1fb79336";

    // Your NFTScan API Key
    public static final String NFTSCAN_API_KEY = "9UAp1GRCyvwyEoiw4M8fUKVt";

    // Your Opensea API Key
    public static final String OPENSEA_API_KEY = "000000000000000000000000 todo";

    // Your Computer Web Proxy
    public static final boolean IF_USE_PROXY = true;
    public static final String PROXY_HOST_NAME = "127.0.0.1";
    public static final int PROXY_PORT = 7890;

    // Transaction Wait Time Limit
    public static final long WAIT_TIME_LIMIT = 3600000; // ms

    // Default Gas Limit
    public static final BigInteger DEFAULT_GAS_LIMIT = new BigInteger("500000");

}
