package baseapi.generics;

public class InterImpl<KKK> implements Inter<KKK> {

    @Override
    public void show(KKK k) {
        System.out.println(k);
    }
}
