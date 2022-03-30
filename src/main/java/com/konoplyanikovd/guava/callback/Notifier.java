package com.konoplyanikovd.guava.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Notifier {

    private final static Logger LOGGER = LoggerFactory.getLogger(Notifier.class);

    public void execute(Caller caller, String msg) throws InterruptedException {
        LOGGER.info("123132=【{}】", msg);

        LOGGER.info("123123");
        TimeUnit.SECONDS.sleep(2);

        caller.getCallBackListener().callBackNotify("123123");
    }
}
