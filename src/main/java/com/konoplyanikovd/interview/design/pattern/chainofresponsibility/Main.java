package com.konoplyanikovd.interview.design.pattern.chainofresponsibility;

import com.konoplyanikovd.interview.design.pattern.chainofresponsibility.impl.CopyrightProcess;
import com.konoplyanikovd.interview.design.pattern.chainofresponsibility.impl.SensitiveWordProcess;
import com.konoplyanikovd.interview.design.pattern.chainofresponsibility.impl.TypoProcess;

public class Main {
    public static void main(String[] args) {
        String msg = "456456==" ;

        MsgProcessChain chain = new MsgProcessChain()
                .addChain(new SensitiveWordProcess())
                .addChain(new TypoProcess())
                .addChain(new CopyrightProcess()) ;

        chain.process(msg) ;
    }
}
