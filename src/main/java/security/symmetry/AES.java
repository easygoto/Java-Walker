package security.symmetry;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class AES {

    public static void main(String[] args) {

        String str = "my heart were go on";
        jdkAes(str);
        bcAes(str);
    }

    private static void jdkAes(String origin) {
        try {
            // 生成 key
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(new SecureRandom());
            SecretKey sk = kg.generateKey();
            byte[] keyBytes = sk.getEncoded();

            // key 转换
            Key key = new SecretKeySpec(keyBytes, "AES");

            // 加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(origin.getBytes());
            System.out.println(Base64.encodeBase64String(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);
            System.out.println(new String(result));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }

    private static void bcAes(String origin) {
        try {
            Security.addProvider(new BouncyCastleProvider());

            // 生成 key
            KeyGenerator kg = KeyGenerator.getInstance("AES", "BC");
            kg.init(new SecureRandom());
            SecretKey sk = kg.generateKey();
            byte[] keyBytes = sk.getEncoded();

            // key 转换
            Key key = new SecretKeySpec(keyBytes, "AES");

            // 加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(origin.getBytes());
            System.out.println(Base64.encodeBase64String(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);
            System.out.println(new String(result));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
