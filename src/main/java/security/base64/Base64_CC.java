package security.base64;

import org.apache.commons.codec.binary.Base64;

public class Base64_CC {

    public static String encode(String origin) {
        byte[] bytes = Base64.encodeBase64(origin.getBytes());
        return new String(bytes);
    }

    public static String decode(String origin) {
        byte[] bytes = Base64.decodeBase64(origin);
        return new String(bytes);
    }
}
