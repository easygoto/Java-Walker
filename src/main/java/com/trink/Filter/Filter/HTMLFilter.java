package com.trink.Filter.Filter;

public class HTMLFilter extends BaseFilter {

    @Override
    void rule() {
        replaceList.put("<", "{");
        replaceList.put(">", "}");
    }
}
