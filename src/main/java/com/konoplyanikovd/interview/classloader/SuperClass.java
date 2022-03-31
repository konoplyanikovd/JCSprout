package com.konoplyanikovd.interview.classloader;

public class SuperClass {

    public static int A = 1;

    static {
        System.out.println("SuperClass init");
    }
}
