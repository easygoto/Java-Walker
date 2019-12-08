package com.trink.Filter.Filter;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseFilter implements Filter {

    Map<String, String> replaceList = new HashMap<>();

    public BaseFilter() {
        rule();
    }

    abstract void rule();

    @Override
    public String doFilter(String message) {
        String result = message;
        for (Map.Entry<String, String> object : replaceList.entrySet()) {
            String key   = object.getKey();
            String value = object.getValue();
            result = result.replace(key, value);
        }
        return result;
    }
}
