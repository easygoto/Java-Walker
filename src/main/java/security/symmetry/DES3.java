package security.symmetry;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class DES3 {

    public static void main(String[] args) {

        String str = "today is very cold";
        jdk3Des(str);
        bc3Des(str);

    }

    public static void jdk3Des(String origin) {
        try {
            // 生成 key
            KeyGenerator kg = KeyGenerator.getInstance("DESede");
//			kg.init(168);
            kg.init(new SecureRandom()); // 生成默认长度
            SecretKey sk = kg.generateKey();
            byte[] bytes = sk.getEncoded();

            // key 转换
            DESedeKeySpec desks = new DESedeKeySpec(bytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            Key convertsk = factory.generateSecret(desks);

            // 加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertsk);
            byte[] result = cipher.doFinal(origin.getBytes());
            System.out.println(Hex.encodeHexString(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertsk);
            result = cipher.doFinal(result);
            System.out.println(new String(result));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }

    public static void bc3Des(String origin) {
        try {
            Security.addProvider(new BouncyCastleProvider());

            // 生成 key
            KeyGenerator kg = KeyGenerator.getInstance("DESede", "BC");
            kg.init(new SecureRandom()); // 生成默认长度
            SecretKey sk = kg.generateKey();
            byte[] bytes = sk.getEncoded();

            // key 转换
            DESedeKeySpec desks = new DESedeKeySpec(bytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            Key convertsk = factory.generateSecret(desks);

            // 加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertsk);
            byte[] result = cipher.doFinal(origin.getBytes());
            System.out.println(Hex.encodeHexString(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertsk);
            result = cipher.doFinal(result);
            System.out.println(new String(result));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
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
