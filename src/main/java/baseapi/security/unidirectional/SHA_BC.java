package baseapi.security.unidirectional;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.util.encoders.Hex;

public class SHA_BC {

    public static String sha1(String origin) {
        Digest digest = new SHA1Digest();
        byte[] bytes = origin.getBytes();
        digest.update(bytes, 0, bytes.length);
        byte[] shaBytes = new byte[digest.getDigestSize()];
        digest.doFinal(shaBytes, 0);
        return Hex.toHexString(shaBytes);
    }
}
