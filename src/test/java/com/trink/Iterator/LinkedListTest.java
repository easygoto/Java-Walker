package com.trink.Iterator;

import org.junit.Test;

public class LinkedListTest {

    @Test
    public void testLinkedList() {
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
