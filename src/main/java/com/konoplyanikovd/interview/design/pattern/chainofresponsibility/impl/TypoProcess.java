package com.konoplyanikovd.interview.design.pattern.chainofresponsibility.impl;

import com.konoplyanikovd.interview.design.pattern.chainofresponsibility.Process;

public class TypoProcess implements Process {
    @Override
    public void doProcess(String msg) {
        System.out.println(msg + "123132");
    }
}
