package com.trink.Strategy;

public class PigBodyComparator implements Comparator {

    @Override
    public int compare(Object object1, Object object2) {
        Pig pig1 = (Pig) object1;
        Pig pig2 = (Pig) object2;
        return Integer.compare(pig1.getBody(), pig2.getBody());
    }
}
