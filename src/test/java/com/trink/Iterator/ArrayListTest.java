package com.trink.Iterator;

import org.junit.Test;

public class ArrayListTest {

    @Test
    public void testSize() {
        Connection conn = new ArrayList();
        for (int i = 0; i < 15; i++) {
            conn.add(i);
        }
        System.out.println(conn.size());

        Iterator it = conn.iterator();
        while (it.hasNext()) {
            Object object = it.next();
            System.out.println(object);
        }
    }
}
