package baseapi.generics;

public class Tool<QQ> {

    private QQ q;

    public Tool(QQ q) {
        super();
        this.q = q;
    }

    public QQ getQ() {
        return q;
    }

    public Tool<QQ> setQ(QQ q) {
        this.q = q;
        return this;
    }

    public void show(QQ q) {
        System.out.println(q);
    }
}
