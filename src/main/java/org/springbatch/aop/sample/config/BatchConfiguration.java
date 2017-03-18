package org.springbatch.aop.sample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    public final static String JOB_NAME = "sampleJob";

    @Bean(name=JOB_NAME)
    public Job sampleJob(JobBuilderFactory jobs
            , @Qualifier("sampleTaskletStep") Step sampleTaskletStep){
        return jobs.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(sampleTaskletStep)
                .build();
    }

    @Bean(name=JOB_NAME + "Bis")
    public Job sampleJobBis(JobBuilderFactory jobs
            , @Qualifier("sampleWorkingStep") Step sampleWorkingStep){
        return jobs.get(JOB_NAME + "Bis")
                .incrementer(new RunIdIncrementer())
                .start(sampleWorkingStep)
                .build();
    }

    @Bean(name=JOB_NAME + "Ter")
    public Job sampleJobTer(JobBuilderFactory jobs
            , @Qualifier("sampleIssueStep") Step sampleIssueStep){
        return jobs.get(JOB_NAME + "Ter")
                .incrementer(new RunIdIncrementer())
                .start(sampleIssueStep)
                .build();
    }

    @Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public MapJobRepositoryFactoryBean mapJobRepositoryFactory(ResourcelessTransactionManager txManager)
            throws Exception {
        MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(txManager);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public JobRepository jobRepository(MapJobRepositoryFactoryBean factory) throws Exception {
        return factory.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }
}
