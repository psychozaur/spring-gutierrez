package com.rybickim.spring.annotated.aop;

import com.rybickim.spring.model.Type;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class Caching {

    private static final Logger logger = LoggerFactory.getLogger(Caching.class);
    private static final Map<String, Object> cache = new HashMap<>();

    @Around("execution(* com.rybickim.spring.service.SearchEngine.*(..))")
    public Object caching(ProceedingJoinPoint pjp) throws Throwable {
        Object result = new Object();
        Type documentType = new Type();

        logger.debug("@@@@(BUFFERING) checking if this call can be buffered...");

        if("findByType".equals(pjp.getSignature().getName()) &&
            pjp.getArgs().length == 1 &&
                pjp.getArgs()[0] instanceof Type){
            documentType = (Type) pjp.getArgs()[0];
            logger.debug("@@@@(BUFFERING) Buffering allowed!!");
            if(cache.containsKey(documentType.getName())){
                logger.debug("@@@@(BUFFERING) Found in the buffer!!!");
                return cache.get(documentType.getName());
            }
            logger.debug("@@@@(BUFFERING) Not found! But buffering is allowed!");
            result = pjp.proceed();
            cache.put(documentType.getName(), result);

            return result;
        }

        return pjp.proceed();

    }
}
