package dp.strategy;

/**
 * @author trink
 */
public interface Comparable {

    /**
     * 比较对象的大小
     *
     * @param object 对象
     * @return int -1(小于) 0(相等) 1(大于)
     */
    int compareTo(Object object);
}
