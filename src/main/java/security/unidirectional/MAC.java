package security.unidirectional;

import org.apache.commons.codec.DecoderException;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MAC {

    private static String ORIGIN = "I'm ready for new life !!!";

    public static void main(String[] args) {

        System.out.println(CCHmacMD5(ORIGIN).toUpperCase());
        System.out.println(jdkHmacMD5(ORIGIN).toUpperCase());
    }

    public static String jdkHmacMD5(String origin) {
        String encode = "";
        try {
            byte[] key = org.apache.commons.codec.binary.Hex.decodeHex(new char[]{'a', 'a'});

            // 还原密钥
            SecretKey resk = new SecretKeySpec(key, "HmacMD5");
            // 实例化 MAC
            Mac mac = Mac.getInstance(resk.getAlgorithm());
            // 初始化 MAC
            mac.init(resk);
            // 执行摘要
            byte[] bytes = mac.doFinal(origin.getBytes());
            encode = Hex.toHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return encode;
    }

    public static String CCHmacMD5(String origin) {
        HMac hm = new HMac(new MD5Digest());
        hm.init(new KeyParameter(Hex.decode("aa")));
        byte[] bytes = origin.getBytes();
        hm.update(bytes, 0, bytes.length);

        byte[] hmBytes = new byte[hm.getMacSize()];
        hm.doFinal(hmBytes, 0);
        return Hex.toHexString(hmBytes);
    }

}
