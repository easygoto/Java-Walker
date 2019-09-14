package security.unidirectional;

public class MD_Main {

    private static String ORIGIN = "I'm trink";

    public static void main(String[] args) {

        System.out.println(MD_BC.md2(ORIGIN).toUpperCase());
        System.out.println(MD_JDK.md2(ORIGIN).toUpperCase());

        System.out.println(MD_BC.md4(ORIGIN).toUpperCase());

        System.out.println(MD_BC.md5(ORIGIN).toUpperCase());
        System.out.println(MD_JDK.md5(ORIGIN).toUpperCase());
    }
}
