package org.springbatch.aop.sample.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class WorkingAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(WorkingAspect.class);

    @Pointcut("execution(* org.springbatch.aop.sample.config.steps..sampleStepReader())")
    private void beanInstanciationAnnotated(){ }

    @Around("beanInstanciationAnnotated()")
    private Object errorHandler(ProceedingJoinPoint pjp) throws Throwable{
        try{
            return pjp.proceed();
        } catch (Throwable e){
            LOGGER.error("WA - Handle error");
            throw e;
        }
    }
}
