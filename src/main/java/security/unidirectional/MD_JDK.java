package security.unidirectional;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD_JDK {

    public static String md5(String origin) {
        String encode = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(origin.getBytes());
            encode = Hex.encodeHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encode;
    }

    public static String md2(String origin) {
        String encode = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] bytes = md.digest(origin.getBytes());
            encode = Hex.encodeHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encode;
    }
}
