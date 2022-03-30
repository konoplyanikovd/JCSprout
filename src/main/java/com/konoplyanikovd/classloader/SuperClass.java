package com.konoplyanikovd.classloader;

public class SuperClass {

    public static int A = 1;

    static {
        System.out.println("SuperClass init");
    }
}
