package com.konoplyanikovd.design.pattern.chainofresponsibility.impl;

import com.konoplyanikovd.design.pattern.chainofresponsibility.Process;

/**
 * Function:
 *
 * @author konoplyanikovd
 *         Date: 2018/10/21 23:56
 * @since JDK 1.8
 */
public class CopyrightProcess implements Process {

    @Override
    public void doProcess(String msg) {
        System.out.println(msg + "版权处理");
    }
}
