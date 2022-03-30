package com.konoplyanikovd.concurrent.future;

public interface Future<T> {

    T get() throws InterruptedException;
}
