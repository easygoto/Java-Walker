package com.trink.Filter.Web;

public interface Filter {

    void doFilter(Request request, Response response, FilterChain filterChain);
}
