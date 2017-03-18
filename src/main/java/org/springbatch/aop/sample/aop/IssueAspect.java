package org.springbatch.aop.sample.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class IssueAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(IssueAspect.class);

    @Pointcut("within(org.springbatch.aop.sample.config.steps..*) && @annotation(org.springbatch.aop.sample.aop.SaveAndErrors)")
    private void beanInstanciationAnnotated(){ }

    @Around("beanInstanciationAnnotated()")
    private Object errorHandler(ProceedingJoinPoint pjp) throws Throwable{
        try{
            return pjp.proceed();
        } catch (Throwable e){
            LOGGER.error("IA - Handle error");
            throw e;
        }
    }
}
