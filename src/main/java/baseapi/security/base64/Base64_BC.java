package baseapi.security.base64;

import org.bouncycastle.util.encoders.Base64;

public class Base64_BC {

    public static String encode(String origin) {
        byte[] bytes = Base64.encode(origin.getBytes());
        return new String(bytes);
    }

    public static String decode(String origin) {
        byte[] bytes = Base64.decode(origin);
        return new String(bytes);
    }

}
