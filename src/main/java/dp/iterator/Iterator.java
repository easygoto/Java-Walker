package dp.iterator;

/**
 * @author trink
 */
public interface Iterator {

    /**
     * 容器的下一个
     *
     * @return Object
     */
    Object next();

    /**
     * 是否有下一个元素
     *
     * @return boolean
     */
    boolean hasNext();
}
