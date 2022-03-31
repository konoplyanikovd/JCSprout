package com.konoplyanikovd.interview.gc;

public class MinorGC {

    private static final int SIZE = 1024 * 1024 ;

    public static void main(String[] args) {
        byte[] one ;
        byte[] four ;

        one = new byte[2 * SIZE] ;
        four = new byte[5 * SIZE] ;
    }
}
