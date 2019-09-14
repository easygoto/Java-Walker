package security.asymmetric;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;

public class Elgamal {

    public static void main(String[] args) {

        String origin = "hope";
        bcEG(origin);
    }

    private static void bcEG(String origin) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance("ElGamal");
            algorithmParameterGenerator.init(160);
            AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();
            DHParameterSpec dhParameterSpec = (DHParameterSpec) algorithmParameters.getParameterSpec(DHParameterSpec.class);
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ElGamal", "BC");
            keyPairGenerator.initialize(dhParameterSpec, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            Cipher cipher = Cipher.getInstance("ElGamal/NONE/NoPadding");
            // 私钥加密、公钥解密——加密
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(origin.getBytes());
            System.out.println(Base64.encodeBase64String(result));

            // 私钥加密、公钥解密——解密
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = cipher.doFinal(result);
            System.out.println(new String(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
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
