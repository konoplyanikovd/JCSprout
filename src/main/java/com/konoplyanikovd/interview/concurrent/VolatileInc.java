package com.konoplyanikovd.interview.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileInc implements Runnable{

    private static volatile int count = 0 ;

    //private static AtomicInteger count = new AtomicInteger() ;

    @Override
    public void run() {
        for (int i=0;i<10000 ;i++){
            count ++ ;
            //count.incrementAndGet() ;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileInc volatileInc = new VolatileInc() ;
        Thread t1 = new Thread(volatileInc,"t1") ;
        Thread t2 = new Thread(volatileInc,"t2") ;
        t1.start();
        //t1.join();

        t2.start();
        //t2.join();
        for (int i=0;i<10000 ;i++){
            count ++ ;
            //count.incrementAndGet();
        }


        System.out.println("Count="+count);
    }
}
