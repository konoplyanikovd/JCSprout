package com.konoplyanikovd.interview.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CustomThreadPool {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomThreadPool.class);
    private final ReentrantLock lock = new ReentrantLock();

    private volatile int miniSize;

    private volatile int maxSize;

    private long keepAliveTime;
    private TimeUnit unit;

    private BlockingQueue<Runnable> workQueue;

    private volatile Set<Worker> workers;

    private AtomicBoolean isShutDown = new AtomicBoolean(false);

    private AtomicInteger totalTask = new AtomicInteger();

    private Object shutDownNotify = new Object();

    public CustomThreadPool(int miniSize, int maxSize, long keepAliveTime,
                            TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        this.miniSize = miniSize;
        this.maxSize = maxSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.workQueue = workQueue;

        workers = new ConcurrentHashSet<>();
    }

    public <T> Future<T> submit(Callable<T> callable) {
        FutureTask<T> future = new FutureTask(callable);
        execute(future);
        return future;
    }

    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("runnable nullPointerException");
        }
        if (isShutDown.get()) {
            LOGGER.info("线程池已经关闭，不能再提交任务！");
            return;
        }

        totalTask.incrementAndGet();

        if (workers.size() < miniSize) {
            addWorker(runnable);
            return;
        }


        boolean offer = workQueue.offer(runnable);
        if (!offer) {

            if (workers.size() < maxSize) {
                addWorker(runnable);
                return;
            } else {
                LOGGER.error("超过最大线程数");
                try {
                    workQueue.put(runnable);
                } catch (InterruptedException e) {

                }
            }

        }
    }

    private void addWorker(Runnable runnable) {
        Worker worker = new Worker(runnable, true);
        worker.startTask();
        workers.add(worker);
    }

    private final class Worker extends Thread {

        private Runnable task;

        private Thread thread;

        private boolean isNewTask;

        public Worker(Runnable task, boolean isNewTask) {
            this.task = task;
            this.isNewTask = isNewTask;
            thread = this;
        }

        public void startTask() {
            thread.start();
        }

        public void close() {
            thread.interrupt();
        }

        @Override
        public void run() {

            Runnable task = null;

            if (isNewTask) {
                task = this.task;
            }

            boolean compile = true ;

            try {
                while ((task != null || (task = getTask()) != null)) {
                    try {
                        task.run();
                    } catch (Exception e) {
                        compile = false ;
                        throw e ;
                    } finally {
                        task = null;
                        int number = totalTask.decrementAndGet();
                        if (number == 0) {
                            synchronized (shutDownNotify) {
                                shutDownNotify.notify();
                            }
                        }
                    }
                }

            } finally {
                boolean remove = workers.remove(this);

                if (!compile){
                    addWorker(null);
                }
                tryClose(true);
            }
        }
    }

    private Runnable getTask() {
        if (isShutDown.get() && totalTask.get() == 0) {
            return null;
        }
        //while (true) {
        //
        //    if (workers.size() > miniSize) {
        //        boolean value = number.compareAndSet(number.get(), number.get() - 1);
        //        if (value) {
        //            return null;
        //        } else {
        //            continue;
        //        }
        //    }

        lock.lock();

        try {
            Runnable task = null;
            if (workers.size() > miniSize) {
                task = workQueue.poll(keepAliveTime, unit);
            } else {
                task = workQueue.take();
            }

            if (task != null) {
                return task;
            }
        } catch (InterruptedException e) {
            return null;
        } finally {
            lock.unlock();
        }

        return null;
        //}
    }


    public void shutdown() {
        isShutDown.set(true);
        tryClose(true);
        //synchronized (shutDownNotify){
        //    while (totalTask.get() > 0){
        //        try {
        //            shutDownNotify.wait();
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //    }
        //}
    }

    public void shutDownNow() {
        isShutDown.set(true);
        tryClose(false);

    }

    public void mainNotify() {
        synchronized (shutDownNotify) {
            while (totalTask.get() > 0) {
                try {
                    shutDownNotify.wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    private void tryClose(boolean isTry) {
        if (!isTry) {
            closeAllTask();
        } else {
            if (isShutDown.get() && totalTask.get() == 0) {
                closeAllTask();
            }
        }

    }

    private void closeAllTask() {
        for (Worker worker : workers) {
            worker.close();
        }
    }

    public int getWorkerCount() {
        return workers.size();
    }

    private final class ConcurrentHashSet<T> extends AbstractSet<T> {

        private ConcurrentHashMap<T, Object> map = new ConcurrentHashMap<>();
        private final Object PRESENT = new Object();

        private AtomicInteger count = new AtomicInteger();

        @Override
        public Iterator<T> iterator() {
            return map.keySet().iterator();
        }

        @Override
        public boolean add(T t) {
            count.incrementAndGet();
            return map.put(t, PRESENT) == null;
        }

        @Override
        public boolean remove(Object o) {
            count.decrementAndGet();
            return map.remove(o) == PRESENT;
        }

        @Override
        public int size() {
            return count.get();
        }
    }
}
