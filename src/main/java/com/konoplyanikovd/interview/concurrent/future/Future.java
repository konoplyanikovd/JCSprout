package com.konoplyanikovd.interview.concurrent.future;

public interface Future<T> {

    T get() throws InterruptedException;
}
