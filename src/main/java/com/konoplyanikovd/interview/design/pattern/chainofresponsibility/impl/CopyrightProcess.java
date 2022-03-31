package com.konoplyanikovd.interview.design.pattern.chainofresponsibility.impl;

import com.konoplyanikovd.interview.design.pattern.chainofresponsibility.Process;

public class CopyrightProcess implements Process {

    @Override
    public void doProcess(String msg) {
        System.out.println(msg + "123456");
    }
}
