package dp.filter.web;

/**
 * @author trink
 */
public interface Filter {

    /**
     * 执行过滤
     *
     * @param request     请求
     * @param response    响应
     * @param filterChain 过滤链
     */
    void doFilter(Request request, Response response, FilterChain filterChain);
}
