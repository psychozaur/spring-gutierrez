package com.rybickim.spring.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeLoggingModule implements MethodBeforeAdvice {

    private static final Logger logger = LoggerFactory.getLogger(BeforeLoggingModule.class);

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        if(logger.isDebugEnabled()){
            logger.debug("@@@@(BEFORE) Method called: " + method.getName());
            if(args.length == 0)
                logger.debug("@@@@(BEFORE) No arguments passed");
            for (Object arg : args)
                logger.debug("@@@@(BEFORE) Argument passed: " + arg);
        }
    }
}
