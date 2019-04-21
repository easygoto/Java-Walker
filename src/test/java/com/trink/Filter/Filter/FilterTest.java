package com.trink.Filter.Filter;

import org.junit.Before;
import org.junit.Test;

public class FilterTest {

    private String       msg;
    private MsgProcessor mp;

    private FilterChain textChain;
    private FilterChain imageChain;
    private FilterChain textImageChain;

    @Before
    public void data() {
        msg = "大家好 :) <script></script> 敏感 傻吊 你他妈";
        mp = new MsgProcessor();
        mp.setMsg(msg);

        textChain = new FilterChain();
        textChain
                .addFilter(new HTMLFilter())
                .addFilter(new SensitiveFilter());

        imageChain = new FilterChain();
        imageChain
                .addFilter(new FaceFilter());

        textImageChain = new FilterChain();
        textImageChain
                .addFilter(textChain)
                .addFilter(imageChain);
    }

    @Test
    public void text() {
        mp.setFilterChain(textChain);
        System.out.println(mp.process());
    }

    @Test
    public void image() {
        mp.setFilterChain(imageChain);
        System.out.println(mp.process());
    }

    @Test
    public void textImage() {
        mp.setFilterChain(textImageChain);
        System.out.println(mp.process());
    }
}
