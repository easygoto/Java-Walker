package security.unidirectional;

public class SHA_Main {

    private static String ORIGIN = "I just look, no speak";

    public static void main(String[] args) {

        System.out.println(SHA_BC.sha1(ORIGIN).toUpperCase());
        System.out.println(SHA_CC.sha1(ORIGIN).toUpperCase());
        System.out.println(SHA_JDK.sha1(ORIGIN).toUpperCase());
    }
}
