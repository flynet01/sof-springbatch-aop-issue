package org.springbatch.aop.sample.config.steps;

import org.springbatch.aop.sample.StubComponent;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleTaskletStepConfiguration {

    public final static String STEP_NAME = "sampleTaskletStep";

    @Bean(name=STEP_NAME)
    @JobScope
    public Step sampleStep(StepBuilderFactory stepBuilderFactory
            , @Qualifier(STEP_NAME + "Task") Tasklet sampleTask) {
        return stepBuilderFactory.get(STEP_NAME)
                .tasklet(sampleTask)
                .build();
    }

    @Bean(STEP_NAME + "Task")
    @StepScope
    public Tasklet sampleTask(StubComponent stubComponent){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                stubComponent.callMe();
                return RepeatStatus.FINISHED;
            }
        };
    }
}
