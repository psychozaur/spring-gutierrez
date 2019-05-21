package com.rybickim.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AroundLoggingModule implements MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AroundLoggingModule.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = new Object();
        if(logger.isDebugEnabled()){
            logger.debug("@@@@(AROUND-BEFORE) Method called: " + invocation.getMethod().getName());
            if(invocation.getArguments().length == 0)
                logger.debug("@@@@(AROUND-BEFORE) No arguments passed");
            for (Object arg : invocation.getArguments())
                logger.debug("@@@@(AROUND-BEFORE) Argument passed: " + arg);
        }

        try {
            if(logger.isDebugEnabled())
                logger.debug("@@@@(AROUND) Processing...");

            result = invocation.proceed();

            if(logger.isDebugEnabled())
                logger.debug("@@@@(AROUND-AFTER) Result: " + result);

            return result;
        } catch (IllegalArgumentException e){
            logger.error("@@@@(AROUND) Reporting exception: " + e.getMessage());
            throw e;
        }
    }
}
