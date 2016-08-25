package com.vv.mydesignframework.utils;

/**
 * Created by VV on 2016/8/23.
 */
public class CheckNotNull {

    public static <T> T check(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    public static void checkArgument(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }
}
