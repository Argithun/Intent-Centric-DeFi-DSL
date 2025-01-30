package tool;

import ast.Node;
import settings.Settings;

import java.util.HashMap;

public class PrivateKeyManager {

    public static HashMap<Node.TriggerStatement, String> statementToPrivateKey = new HashMap<>();

    public static void setPrivateKey(Node root) {
        String privateKey = null;

        for (Node.TriggerStatement triggerStatement : root.getTriggerStatements()) {
            if (triggerStatement.getStatement() instanceof Node.AccountStatement) {
                privateKey = ((Node.AccountStatement) triggerStatement.getStatement()).getPrivateKey().getContent();
                if (!checkPrivateKeyFormat(privateKey)) {
                    System.out.println("Error format of your private key: " + privateKey);
                    throw new RuntimeException("Please obey the private key format: [0-9a-fA-F]+");
                }
            } else {
                if (privateKey == null) {
                    throw new RuntimeException("Please set your private key (switch account statement) before transaction.");
                }
                statementToPrivateKey.put(triggerStatement, privateKey);
            }
        }
    }

    private static boolean checkPrivateKeyFormat(String privateKey) {
        String regex = "^[0-9a-fA-F]+$";
        return privateKey != null && privateKey.matches(regex);
    }


}
