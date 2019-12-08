package com.trink.Filter.Filter;

public class FaceFilter extends BaseFilter {

    @Override
    void rule() {
        replaceList.put(":)", "^_^");
    }
}
