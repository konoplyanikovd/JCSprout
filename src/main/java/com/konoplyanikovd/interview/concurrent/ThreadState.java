package com.konoplyanikovd.interview.concurrent;

public class ThreadState {

    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaiting").start();
        new Thread(new Waiting(),"Waiting").start();
        new Thread(new Blocked(),"Blocked1").start();
        new Thread(new Blocked(),"Blocked2").start();
    }

    static class TimeWaiting implements Runnable{

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Waiting implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    static class Blocked implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (Blocked.class){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
