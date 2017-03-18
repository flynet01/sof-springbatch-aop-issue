package org.springbatch.aop.sample.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class WorkingAnnotedAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(WorkingAnnotedAspect.class);

    @Pointcut("@annotation(org.springbatch.aop.sample.aop.OtherAnnotation)")
    private void beanInstanciationAnnotated(){ }

    @Around("beanInstanciationAnnotated()")
    private Object errorHandler(ProceedingJoinPoint pjp) throws Throwable{
        LOGGER.warn("WAA - Handle annotation aspect");
        return pjp.proceed();
    }
}
