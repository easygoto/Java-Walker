package dp.iterator;

import org.junit.Test;

public class IteratorTest {

    @Test
    public void arrayList() {
        Connection conn = new ArrayList();
        for (int i = 0; i < 15; i++) {
            conn.add(new Cat(i));
        }
        System.out.println(conn.size());

        Iterator it = conn.iterator();
        while (it.hasNext()) {
            Cat cat = (Cat) it.next();
            System.out.println(cat);
        }
    }

    @Test
    public void linkedList() {
        Connection conn = new LinkedList();
        for (int i = 0; i < 15; i++) {
            conn.add(new Cat(i));
        }
        System.out.println(conn.size());

        Iterator it = conn.iterator();
        while (it.hasNext()) {
            Cat cat = (Cat) it.next();
            System.out.println(cat);
        }
    }
}
