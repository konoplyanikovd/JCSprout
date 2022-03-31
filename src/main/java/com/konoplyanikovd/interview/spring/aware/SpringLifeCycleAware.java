package com.konoplyanikovd.interview.spring.aware;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringLifeCycleAware implements ApplicationContextAware {
    private final static Logger LOGGER = LoggerFactory.getLogger(SpringLifeCycleAware.class);

    private ApplicationContext _applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        _applicationContext = applicationContext;
        LOGGER.info("SpringLifeCycleAware start");
    }
}
