package security.base64;

public class Base64_Main {

    private static String DECODE = "My name is Da Shen";
    private static String ENCODE = "TXkgbmFtZSBpcyBsaWppYW4sIGFuZCBJJ20gRGFTaGVu";

    public static void main(String[] args) {
        System.out.println(Base64_JDK.encode(DECODE));
        System.out.println(Base64_JDK.decode(ENCODE));

        System.out.println(Base64_CC.encode(DECODE));
        System.out.println(Base64_CC.decode(ENCODE));

        System.out.println(Base64_BC.encode(DECODE));
        System.out.println(Base64_BC.decode(ENCODE));
    }
}
