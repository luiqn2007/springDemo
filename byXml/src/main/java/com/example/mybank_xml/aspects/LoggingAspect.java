package com.example.mybank_xml.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class LoggingAspect {

    private final Logger logger = LogManager.getLogger();

    public void log(JoinPoint joinPoint) {
        logger.info("Enter: {}'s {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName());

        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logger.info("Argument {}: {}", i, args[i]);
        }
    }

    public Object logInvokeTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnObj;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(joinPoint.getSignature().getName());
        try {
            returnObj = joinPoint.proceed();
        } finally {
            stopWatch.stop();
            logger.info(stopWatch.prettyPrint());
        }
        return returnObj;
    }
}
