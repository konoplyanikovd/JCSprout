package com.konoplyanikovd.spring;

import com.konoplyanikovd.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringLifeCycle{

    private final static Logger LOGGER = LoggerFactory.getLogger(SpringLifeCycle.class);
    public void start(){
        LOGGER.info("SpringLifeCycle start");
    }

    public void destroy(){
        LOGGER.info("SpringLifeCycle destroy");
    }
}
