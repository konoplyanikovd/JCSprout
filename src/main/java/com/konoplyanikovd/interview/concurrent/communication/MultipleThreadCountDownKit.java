package com.konoplyanikovd.interview.concurrent.communication;

import java.util.concurrent.atomic.AtomicInteger;

public final class MultipleThreadCountDownKit {

    private AtomicInteger counter;

    private Object notify ;

    private Notify notifyListen ;

    public MultipleThreadCountDownKit(int number){
        if (number < 0) {
            throw new IllegalArgumentException("counter < 0");
        }
        counter = new AtomicInteger(number) ;
        notify = new Object() ;
    }

    public void setNotify(Notify notify){
        notifyListen = notify ;
    }

    public void countDown(){

        if (counter.get() <= 0){
            return;
        }

        int count = this.counter.decrementAndGet();
        if (count < 0){
            throw new RuntimeException("concurrent error") ;
        }

        if (count == 0){
            synchronized (notify){
                notify.notify();
            }
        }

    }

    public void await() throws InterruptedException {
        synchronized (notify){
            while (counter.get() > 0){
                notify.wait();
            }

            if (notifyListen != null){
                notifyListen.notifyListen();
            }

        }
    }

}
