package org.springbatch.aop.sample.config.steps;

import org.springbatch.aop.sample.config.reader.CustomItemReaderFactory;
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
public class SampleWorkingStepConfiguration {

    public final static String STEP_NAME = "sampleWorkingStep";

    @Bean(name=STEP_NAME)
    @JobScope
    public Step sampleStep(StepBuilderFactory stepBuilderFactory
            , @Qualifier(STEP_NAME + "Reader") ItemReader sampleWorkingStepReader
            , @Qualifier(STEP_NAME + "Writer") ItemWriter sampleWorkingStepWriter) {
        return stepBuilderFactory.get(STEP_NAME)
                .chunk(1)
                .reader(sampleWorkingStepReader)
                .writer(sampleWorkingStepWriter)
                .build();
    }

    @Bean(name=STEP_NAME + "Reader")
    @StepScope
    public ItemReader<String> sampleWorkingStepReader(CustomItemReaderFactory customItemReaderFactory) throws Exception {
        return customItemReaderFactory.createNewReader();
    }

    @Bean(name=STEP_NAME + "Writer")
    @StepScope
    public ItemWriter<String> sampleWorkingStepWriter() {
        return new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> list) throws Exception {
                list.stream().forEach(System.out::println);
            }
        };
    }
}
