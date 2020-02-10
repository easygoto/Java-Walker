package dp.filter.normal;

/**
 * @author trink
 */
public class MsgProcessor {

    private String msg;

    private FilterChain filterChain;

    public FilterChain getFilterChain() {
        return filterChain;
    }

    public void setFilterChain(FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    public String getMsg() {
        return msg;
    }

    public MsgProcessor setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String process() {

        return filterChain.doFilter(msg);
    }
}
