package dp.filter.normal;

/**
 * @author trink
 */
public interface Filter {

    /**
     * 执行过滤
     *
     * @param message 需要过滤的信息
     * @return String
     */
    String doFilter(String message);
}
