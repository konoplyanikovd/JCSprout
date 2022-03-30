package com.konoplyanikovd.actual;

public class TwoThreadWaitNotifySimple {

    private boolean flag = false;

    public static void main(String[] args) {
        TwoThreadWaitNotifySimple twoThread = new TwoThreadWaitNotifySimple();

        Thread t1 = new Thread(new OuNum(twoThread));
        t1.setName("t1");


        Thread t2 = new Thread(new JiNum(twoThread));
        t2.setName("t2");

        t1.start();
        t2.start();
    }

    public static class OuNum implements Runnable {
        private TwoThreadWaitNotifySimple number;

        public OuNum(TwoThreadWaitNotifySimple number) {
            this.number = number;
        }

        @Override
        public void run() {
            for (int i = 0; i < 11; i++) {
                synchronized (TwoThreadWaitNotifySimple.class) {
                    if (number.flag) {
                        if (i % 2 == 0) {
                            System.out.println(Thread.currentThread().getName() + "+-+偶数" + i);

                            number.flag = false;
                            TwoThreadWaitNotifySimple.class.notify();
                        }

                    } else {
                        try {
                            TwoThreadWaitNotifySimple.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static class JiNum implements Runnable {
        private TwoThreadWaitNotifySimple number;

        public JiNum(TwoThreadWaitNotifySimple number) {
            this.number = number;
        }

        @Override
        public void run() {
            for (int i = 0; i < 11; i++) {
                synchronized (TwoThreadWaitNotifySimple.class) {
                    if (!number.flag) {
                        if (i % 2 == 1) {
                            System.out.println(Thread.currentThread().getName() + "+-+奇数" + i);

                            number.flag = true;
                            TwoThreadWaitNotifySimple.class.notify();
                        }

                    } else {
                        try {
                            TwoThreadWaitNotifySimple.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
