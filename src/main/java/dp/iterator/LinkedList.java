package dp.iterator;

/**
 * @author trink
 */
public class LinkedList implements Connection {

    private Node head = null;
    private Node tail = null;
    private int  index = 0;

    private static class Node {
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

    @Override
    public void add(Object o) {
        Node node = new Node(o, null);
        if (head == null) {
            head = node;
            tail = node;
        }
        tail.setNext(node);
        tail = node;
        index++;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator {

        private int currentIndex = 0;

        @Override
        public Object next() {
            int tempIndex = currentIndex;
            Node tempNode = head;
            while (tempIndex > 0) {
                tempNode = tempNode.next;
                tempIndex--;
            }
            currentIndex++;
            return tempNode.data;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < index;
        }
    }
}
