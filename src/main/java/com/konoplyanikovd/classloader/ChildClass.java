package com.konoplyanikovd.classloader;

public class ChildClass extends SuperClass {
    static {
        System.out.println("ChildClass init");
    }
}
