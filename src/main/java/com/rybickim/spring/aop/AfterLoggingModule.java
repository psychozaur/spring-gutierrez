package com.rybickim.spring.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AfterLoggingModule implements AfterReturningAdvice {

    private static final Logger logger = LoggerFactory.getLogger(AfterLoggingModule.class);

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        if(logger.isDebugEnabled()){
            logger.debug("@@@@(AFTER) Method called: " + method.getName());
            if(args.length == 0)
                logger.debug("@@@@(AFTER) No arguments passed");
            for (Object arg : args)
                logger.debug("@@@@(AFTER) Argument passed: " + arg);
            logger.debug("@@@@(AFTER) Result: " + returnValue);

        }
    }
}
