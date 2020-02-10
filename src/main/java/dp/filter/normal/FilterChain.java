package dp.filter.normal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author trink
 */
public class FilterChain implements Filter {

    private List<Filter> filters = new ArrayList<>();

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public FilterChain addFilter(Filter filter) {
        this.filters.add(filter);
        return this;
    }

    public FilterChain addFilter(List<Filter> filters) {
        this.filters.addAll(filters);
        return this;
    }

    @Override
    public String doFilter(String message) {
        String result = message;
        for (Filter filter : filters) {
            result = filter.doFilter(result);
        }
        return result;
    }
}
