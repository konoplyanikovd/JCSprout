package com.konoplyanikovd.concurrent.future;

/**
 * Function:
 *
 * @author konoplyanikovd
 * Date: 2019-06-03 23:54
 * @since JDK 1.8
 */
public interface Callable<T> {

    /**
     * 执行任务
     * @return 执行结果
     */
    T call() ;
}
