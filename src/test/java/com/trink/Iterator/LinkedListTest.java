package com.trink.Iterator;

import org.junit.Test;

public class LinkedListTest {

    @Test
    public void testSize() {
        Connection conn = new LinkedList();
        for (int i = 0; i < 15; i++) {
            conn.add(i);
        }
        System.out.println(conn.size());
    }
}
