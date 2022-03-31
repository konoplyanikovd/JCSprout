package com.konoplyanikovd.interview.classloader;

public class ChildClass extends SuperClass {
    static {
        System.out.println("ChildClass init");
    }
}
