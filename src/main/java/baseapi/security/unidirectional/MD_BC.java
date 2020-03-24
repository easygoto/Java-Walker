package baseapi.security.unidirectional;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.util.encoders.Hex;

public class MD_BC {

    public static String md5(String origin) {
        Digest digest = new MD5Digest();
        byte[] bytes = origin.getBytes();
        digest.update(bytes, 0, bytes.length);
        byte[] mdBytes = new byte[digest.getDigestSize()];
        digest.doFinal(mdBytes, 0);
        return Hex.toHexString(mdBytes);
    }

    public static String md2(String origin) {
        Digest digest = new MD2Digest();
        byte[] bytes = origin.getBytes();
        digest.update(bytes, 0, bytes.length);
        byte[] mdBytes = new byte[digest.getDigestSize()];
        digest.doFinal(mdBytes, 0);
        return Hex.toHexString(mdBytes);
    }

    public static String md4(String origin) {
        Digest digest = new MD4Digest();
        byte[] bytes = origin.getBytes();
        digest.update(bytes, 0, bytes.length);
        byte[] mdBytes = new byte[digest.getDigestSize()];
        digest.doFinal(mdBytes, 0);
        return Hex.toHexString(mdBytes);
    }
}
