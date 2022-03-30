package com.konoplyanikovd.concurrent.future;

/**
 * Function:
 *
 * @author konoplyanikovd
 * Date: 2019-06-03 23:55
 * @since JDK 1.8
 */
public interface Future<T> {

    /**
     * 获取
     * @return 结果
     * @throws InterruptedException
     */
    T get() throws InterruptedException;
}
