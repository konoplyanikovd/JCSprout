package com.konoplyanikovd.interview.proxy.jdk.impl;

import com.konoplyanikovd.interview.proxy.jdk.ISubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ISubjectImpl implements ISubject {
    private final static Logger LOGGER = LoggerFactory.getLogger(ISubjectImpl.class);
 
    @Override
    public void execute() {
        LOGGER.info("ISubjectImpl execute");
    }
}
