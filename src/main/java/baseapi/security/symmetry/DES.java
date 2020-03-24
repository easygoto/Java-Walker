package baseapi.security.symmetry;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class DES {

    public static void main(String[] args) {

        String str = "very old techlonogy";
        jdkDes(str);
        bcDes(str);
    }

    public static void jdkDes(String origin) {
        try {
            // 生成 key
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            kg.init(56);
            SecretKey sk = kg.generateKey();
            byte[] bytes = sk.getEncoded();

            // key 转换
            DESKeySpec desks = new DESKeySpec(bytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertsk;
            convertsk = factory.generateSecret(desks);

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
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

    public static void bcDes(String origin) {
        try {
            Security.addProvider(new BouncyCastleProvider());

            // 生成 key
            KeyGenerator kg;
            kg = KeyGenerator.getInstance("DES", "BC");
            kg.init(56);
            SecretKey sk = kg.generateKey();
            byte[] bytes = sk.getEncoded();

            // key 转换
            DESKeySpec desks = new DESKeySpec(bytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertsk;
            convertsk = factory.generateSecret(desks);

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
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
