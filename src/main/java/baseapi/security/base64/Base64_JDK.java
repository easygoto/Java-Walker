package baseapi.security.base64;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64_JDK {

    public static String encode(String origin) {
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(origin.getBytes());
    }

    public static String decode(String origin) {
        Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(origin);
        return new String(bytes);
    }
}
