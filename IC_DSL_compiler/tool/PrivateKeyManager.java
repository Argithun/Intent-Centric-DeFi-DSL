package tool;

import settings.Settings;

public class PrivateKeyManager {

    public static void switchAccount(String privateKey) {
        if (checkPrivateKeyFormat(privateKey)) {
            Settings.ACCOUNT_PRIVATE_KEY = privateKey;
        } else {
            System.out.println("Please obey the private key format: [0-9a-fA-F]+");
        }
    }

    public static boolean checkPrivateKeyFormat(String privateKey) {
        String regex = "^[0-9a-fA-F]+$";
        return privateKey != null && privateKey.matches(regex);
    }


}
