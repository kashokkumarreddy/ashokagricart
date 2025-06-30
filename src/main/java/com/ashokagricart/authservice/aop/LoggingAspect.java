package com.ashokagricart.authservice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.agrowise.authservice.service.impl.*.*(..))")
    public void serviceLayerPointcut() {}

    @Before("serviceLayerPointcut()")
    public void logBeforeServiceMethod(JoinPoint joinPoint){
        logger.info("Entering method: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }
    @AfterReturning(pointcut = "serviceLayerPointcut()", returning = "result")
    public void logAfterServiceMethod(JoinPoint joinPoint, Object result){
        logger.info("Existing method: {} with result: {}",
                joinPoint.getSignature().toShortString(),
                result);
    }

    @AfterThrowing(pointcut = "serviceLayerPointcut()",throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception){
        logger.error("Exception in method: {} with cause: {}",
                joinPoint.getSignature().toShortString(),
                exception.getMessage());
    }

}

