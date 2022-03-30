package com.konoplyanikovd.synchronize;

public class Synchronize {

    public static void main(String[] args) {
        synchronized (Synchronize.class){
            System.out.println("Synchronize");
        }
    }
}
