package com.konoplyanikovd.basic;

import java.lang.reflect.Field;

public class StringTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String a = "123";
        String b = "123" ;
        String c = new String("123") ;

        System.out.println("a=b:" + (a == b));
        System.out.println("a=c:" + (a == c));

        System.out.println("a=" + a);

        a = "456";
        System.out.println("a=" + a);


        Field value = a.getClass().getDeclaredField("value");
        value.setAccessible(true) ;

        char[] values = (char[]) value.get(a);
        values[0] = '9' ;

        System.out.println(a);
    }
}
