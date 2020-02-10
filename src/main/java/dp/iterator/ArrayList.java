package dp.iterator;

/**
 * @author trink
 */
public class ArrayList implements Connection {

    private Object[] objectList = new Object[10];
    private int      index      = 0;

    @Override
    public void add(Object object) {
        if (index == objectList.length) {
            Object[] newObjectList = new Object[objectList.length * 2];
            System.arraycopy(objectList, 0, newObjectList, 0, objectList.length);
            objectList = newObjectList;
        }
        objectList[index] = object;
        index++;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public Iterator iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator {

        private int currentIndex = 0;

        @Override
        public Object next() {
            return objectList[currentIndex++];
        }

        @Override
        public boolean hasNext() {
            return currentIndex < index;
        }
    }
}
