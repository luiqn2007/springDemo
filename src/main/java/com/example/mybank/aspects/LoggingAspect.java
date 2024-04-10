package com.example.mybank.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class LoggingAspect {

    private final Logger logger = LogManager.getLogger();

    @Before("execution(* com.example.mybank.service.*Service.*(..))")
    public void log(JoinPoint joinPoint) {
        logger.info("Enter: {}'s {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName());

        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logger.info("Argument {}: {}", i, args[i]);
        }
    }

    @Around("execution(* com.example.mybank.service.*Service.*(..))")
    public Object logInvokeTime(ProceedingJoinPoint joinPoint) {
        Object returnObj = null;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(joinPoint.getSignature().getName());
        try {
            returnObj = joinPoint.proceed();
        } catch (Throwable ignored) {
        }
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
        return returnObj;
    }
}
