package com.rybickim.spring.aop;

import com.rybickim.spring.model.Type;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CachingModule implements MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(CachingModule.class);
    private static final Map<String, Object> cache = new HashMap<>();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = new Object();
        Type documentType = new Type();

        logger.debug("@@@@(BUFFERING) checking if this call can be buffered...");

        if("findByType".equals(invocation.getMethod().getName()) &&
            invocation.getArguments().length == 1 &&
                invocation.getArguments()[0] instanceof Type){
            documentType = (Type) invocation.getArguments()[0];
            logger.debug("@@@@(BUFFERING) Buffering allowed!!");
            if(cache.containsKey(documentType.getName())){
                logger.debug("@@@@(BUFFERING) Found in the buffer!!!");
                return cache.get(documentType.getName());
            }
            logger.debug("@@@@(BUFFERING) Not found! But buffering is allowed!");
            result = invocation.proceed();
            cache.put(documentType.getName(), result);

            return result;
        }

        return invocation.proceed();

    }
}
