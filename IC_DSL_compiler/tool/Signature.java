package tool;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SignatureException;
import java.util.Arrays;

public class Signature {
    public static String signPrefixedMessage(String content, String privateKey) {

        // 如果验签不成功，就不需要 Hash.sha3 直接 content.getBytes()就可以了
        // 原文信息字节数组
        // byte[] contentHashBytes = Hash.sha3(content.getBytes());
        byte[] contentHashBytes = content.getBytes();
        // 根据私钥获取凭证对象
        Credentials credentials = Credentials.create(privateKey);
        Sign.SignatureData signMessage = Sign.signPrefixedMessage(contentHashBytes, credentials.getEcKeyPair());

        byte[] r = signMessage.getR();
        byte[] s = signMessage.getS();
        byte[] v = signMessage.getV();

        byte[] signByte = Arrays.copyOf(r, v.length + r.length + s.length);
        System.arraycopy(s, 0, signByte, r.length, s.length);
        System.arraycopy(v, 0, signByte, r.length + s.length, v.length);

        return Numeric.toHexString(signByte);
    }

    public static Boolean validate(String signature, String content, String walletAddress) throws SignatureException {
        if (content == null) {
            return false;
        }
        // 如果验签不成功，就不需要Hash.sha3 直接content.getBytes()就可以了
        // 原文字节数组
        // byte[] msgHash = Hash.sha3(content.getBytes());
        byte[] msgHash = content.getBytes();
        // 签名数据
        byte[] signatureBytes = Numeric.hexStringToByteArray(signature);
        byte v = signatureBytes[64];
        if (v < 27) {
            v += 27;
        }

        //通过摘要和签名后的数据，还原公钥
        Sign.SignatureData signatureData = new Sign.SignatureData(
                v,
                Arrays.copyOfRange(signatureBytes, 0, 32),
                Arrays.copyOfRange(signatureBytes, 32, 64));
        // 签名的前缀消息到密钥
        BigInteger publicKey = Sign.signedPrefixedMessageToKey(msgHash, signatureData);
        // 得到公钥(私钥对应的钱包地址)
        String parseAddress = "0x" + Keys.getAddress(publicKey);
        // 将钱包地址进行比对
        return parseAddress.equalsIgnoreCase(walletAddress);
    }

    public static String keccak256Hash(String input) {
        Keccak.Digest256 keccak256 = new Keccak.Digest256();
        byte[] hash = keccak256.digest(input.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }

        System.out.println(hexString);
        return hexString.toString();
    }

}
