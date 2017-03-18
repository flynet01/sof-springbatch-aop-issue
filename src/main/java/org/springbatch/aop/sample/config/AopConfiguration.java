package org.springbatch.aop.sample.config;

import org.springbatch.aop.sample.aop.IssueAspect;
import org.springbatch.aop.sample.aop.WorkingAnnotedAspect;
import org.springbatch.aop.sample.aop.WorkingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfiguration {

    @Bean
    public IssueAspect issueAspect(){
        return new IssueAspect();
    }

    @Bean
    public WorkingAspect workingAspect(){
        return new WorkingAspect();
    }

    @Bean
    public WorkingAnnotedAspect workingAnnotedAspect(){
        return new WorkingAnnotedAspect();
    }
}
