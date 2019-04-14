package com.trink.Iterator;

public class LinkedList implements Connection {

    private Node head = null;
    private Node tail = null;
    private int  size = 0;

    private class Node {
        private Object data;
        private Node   next;

        public Node(Object data) {
            this.data = data;
        }

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }
    }

    public void add(Object o) {
        Node node = new Node(o, null);
        if (head == null) {
            head = node;
            tail = node;
        }
        tail.setNext(node);
        tail = node;
        size++;
    }

    public int size() {
        return size;
    }

    public Iterator iterator() {
        return null;
    }
}
