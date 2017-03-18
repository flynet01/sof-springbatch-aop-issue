package org.springbatch.aop.sample.config.steps;

import org.springbatch.aop.sample.aop.SaveAndErrors;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SampleIssueStepConfiguration {

    public final static String STEP_NAME = "sampleIssueStep";

    @Bean(name=STEP_NAME)
    @JobScope
    public Step sampleStep(StepBuilderFactory stepBuilderFactory
            , @Qualifier(STEP_NAME + "Reader") ItemReader sampleStepReader
            , @Qualifier(STEP_NAME + "Writer") ItemWriter sampleStepWriter) {
        return stepBuilderFactory.get(STEP_NAME)
                .chunk(1)
                .reader(sampleStepReader)
                .writer(sampleStepWriter)
                .build();
    }

    @Bean(name=STEP_NAME + "Reader")
    @StepScope
    @SaveAndErrors
    public ItemReader<String> sampleStepReader() throws Exception {
        throw new Exception("error");
    }

    @Bean(name=STEP_NAME + "Writer")
    @StepScope
    public ItemWriter<String> sampleStepWriter() {
        return new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> list) throws Exception {
                list.stream().forEach(System.out::println);
            }
        };
    }
}
