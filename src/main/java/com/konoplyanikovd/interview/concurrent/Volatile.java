package com.konoplyanikovd.interview.concurrent;

import java.util.Scanner;

public class Volatile implements Runnable{

    private static volatile boolean flag = true ;

    @Override
    public void run() {
        while (flag){
        }
        System.out.println(Thread.currentThread().getName() +"执行完毕");
    }

    public static void main(String[] args) throws InterruptedException {
        Volatile aVolatile = new Volatile();
        new Thread(aVolatile,"thread A").start();



        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String value = sc.next();
            if(value.equals("1")){

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        aVolatile.stopThread();
                    }
                }).start();

                break ;
            }
        }
    }

    private void stopThread(){
        flag = false ;
    }
}
