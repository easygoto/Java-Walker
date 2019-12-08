package com.trink.Strategy;

public class DataSorter {

    private static void swap(Object[] arr, int x, int y) {
        Object temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void sort(Object[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = i + 1; j < list.length; j++) {
                Comparable obj1 = (Comparable) list[i];
                Comparable obj2 = (Comparable) list[j];
                if (obj1.compareTo(obj2) > 0) {
                    swap(list, i, j);
                }
            }
        }
    }

    public static void show(Object[] arr) {
        for (Object value : arr) {
            System.out.println(value);
        }
    }
}
