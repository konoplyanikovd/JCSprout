package com.konoplyanikovd.hystrix;

import com.netflix.hystrix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class CommandUser extends HystrixCommand<String> {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommandUser.class);

    private String userName;

    public CommandUser(String userName) {

        super(Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("UserGroup"))
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserPool"))
                    .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                    .withCoreSize(10)
                    .withKeepAliveTimeMinutes(5)
                    .withMaxQueueSize(10)
                    .withQueueSizeRejectionThreshold(10000))
                    .andCommandPropertiesDefaults(
                
                HystrixCommandProperties.Setter()
                    .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
        )
        ;
        this.userName = userName;
    }


    @Override
    public String run() throws Exception {

        LOGGER.info("userName=[{}]", userName);

        TimeUnit.MILLISECONDS.sleep(100);
        return "userName=" + userName;
    }
}
