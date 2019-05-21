package com.rybickim.spring.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class ThrowsLoggingModule implements ThrowsAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ThrowsLoggingModule.class);

    public void afterThrowing(Method method, Object[] args, Object target, Exception e) throws Throwable {
        if(logger.isDebugEnabled()){
            logger.debug("@@@@(THROWS) Method called: " + method.getName());
            if(args.length == 0)
                logger.debug("@@@@(THROWS) No arguments passed");
            for (Object arg : args)
                logger.debug("@@@@(THROWS) Argument passed: " + arg);
            logger.debug("@@@@(THROWS) Exception: " + e.getMessage());

        }
    }
}
