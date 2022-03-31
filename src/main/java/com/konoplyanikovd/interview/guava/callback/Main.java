package com.konoplyanikovd.interview.guava.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Notifier notifier = new Notifier() ;

        Caller caller = new Caller() ;
        caller.setNotifier(notifier) ;
        caller.setQuestion("你在哪儿！");
        caller.setCallBackListener(new CallBackListener() {
            @Override
            public void callBackNotify(String msg) {
                LOGGER.info("123123" ,msg);
            }
        });

        caller.call();
    }
}
