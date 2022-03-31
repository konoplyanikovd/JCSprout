package com.konoplyanikovd.interview.spring.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class AnnotationBean {
    private final static Logger LOGGER = LoggerFactory.getLogger(AnnotationBean.class);

    @PostConstruct
    public void start(){
        LOGGER.info("AnnotationBean start");
    }

    @PreDestroy
    public void destroy(){
        LOGGER.info("AnnotationBean destroy");
    }
}
