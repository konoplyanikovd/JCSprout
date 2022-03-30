package com.konoplyanikovd.design.pattern.chainofresponsibility;

import com.konoplyanikovd.design.pattern.chainofresponsibility.impl.CopyrightProcess;
import com.konoplyanikovd.design.pattern.chainofresponsibility.impl.SensitiveWordProcess;
import com.konoplyanikovd.design.pattern.chainofresponsibility.impl.TypoProcess;

/**
 * Function:
 *
 * @author konoplyanikovd
 *         Date: 2018/10/21 23:07
 * @since JDK 1.8
 */
public class Main {
    public static void main(String[] args) {
        String msg = "内容内容内容==" ;

        MsgProcessChain chain = new MsgProcessChain()
                .addChain(new SensitiveWordProcess())
                .addChain(new TypoProcess())
                .addChain(new CopyrightProcess()) ;

        chain.process(msg) ;
    }
}
