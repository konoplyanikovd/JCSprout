package com.konoplyanikovd.guava.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Caller {

    private final static Logger LOGGER = LoggerFactory.getLogger(Caller.class);

    private CallBackListener callBackListener ;

    private Notifier notifier ;

    private String question ;

    public void call(){

        LOGGER.info("开始提问");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    notifier.execute(Caller.this,question);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        LOGGER.info("123123");
    }


    public Notifier getNotifier() {
        return notifier;
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }


    public CallBackListener getCallBackListener() {
        return callBackListener;
    }

    public void setCallBackListener(CallBackListener callBackListener) {
        this.callBackListener = callBackListener;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Caller{" +
                "callBackListener=" + callBackListener +
                ", notifier=" + notifier +
                ", question='" + question + '\'' +
                '}';
    }
}
