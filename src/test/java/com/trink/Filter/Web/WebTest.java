package com.trink.Filter.Web;

import org.junit.Test;

public class WebTest {

    @Test
    public void test() {
        String msg = "大家好 :) <script></script> 敏感 傻吊 你他妈";

        Request request = new Request();
        request.setMessage(msg);

        Response response = new Response();
        response.setMessage("Response");

        FilterChain filterChain = new FilterChain();
        filterChain
                .addFilter(new HTMLFilter())
                .addFilter(new FaceFilter());
        filterChain.doFilter(request, response, filterChain);

        System.out.println(request.getMessage());
        System.out.println(response.getMessage());
    }
}
