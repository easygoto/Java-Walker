package dp.filter.web;

import java.util.ArrayList;
import java.util.List;

/**
 * @author trink
 */
public class FilterChain implements Filter {

    private int index = 0;

    private List<Filter> filters = new ArrayList<>();

    public FilterChain addFilter(Filter filter) {
        this.filters.add(filter);
        return this;
    }

    public FilterChain addFilter(List<Filter> filters) {
        this.filters.addAll(filters);
        return this;
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        if (index < filters.size()) {
            Filter filter = filters.get(index);
            index += 1;
            filter.doFilter(request, response, filterChain);
        }
    }
}
