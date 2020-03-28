package example.security.unidirectional;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author trink
 */
public class SHA_CC {
    public static String sha1(String origin) {
        return DigestUtils.sha1Hex(origin);
    }

}
