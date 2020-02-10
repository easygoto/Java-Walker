package dp.strategy;

/**
 * @author trink
 */
public interface Comparator {

    /**
     * 比较对象的大小
     *
     * @param object1 对象1
     * @param object2 对象2
     * @return int -1(小于) 0(相等) 1(大于)
     */
    int compare(Object object1, Object object2);
}
