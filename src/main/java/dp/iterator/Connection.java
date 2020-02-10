package dp.iterator;

/**
 * @author trink
 */
public interface Connection {

    /**
     * 添加一个元素
     *
     * @param object 任意对象
     */
    void add(Object object);

    /**
     * 容器大小
     *
     * @return int
     */
    int size();

    /**
     * 容器的迭代方法
     *
     * @return Iterator
     */
    Iterator iterator();
}
