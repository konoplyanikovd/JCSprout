package com.konoplyanikovd.interview.actual;

public class TwoThreadNonBlocking implements Runnable {

    private volatile static int flag = 1;

    private int start;
    private int end;
    private String name;

    private TwoThreadNonBlocking(int start, int end, String name) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        while (start <= end) {
            int f = flag;
            if ((start & 0x01) == f) {
                System.out.println(name + "+-+" + start);
                start += 2;
                flag ^= 0x1;
            }
        }
    }


    public static void main(String[] args) {
        new Thread(new TwoThreadNonBlocking(1, 100, "t1")).start();
        new Thread(new TwoThreadNonBlocking(2, 100, "t2")).start();
    }
}
