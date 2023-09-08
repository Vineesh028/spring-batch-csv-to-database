package com.batch.sample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.batch.sample.entity.UserEntity;
import com.batch.sample.listener.JobListener;
import com.batch.sample.repository.UserRepository;

@Configuration
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	UserRepository repository;

	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob").incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}

	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").<UserEntity, UserEntity>chunk(1).reader(userReader())
				.processor(processor()).writer(writer()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobListener();
	}

	@Bean
	public FlatFileItemReader<UserEntity> userReader() {
		return new FlatFileItemReaderBuilder<UserEntity>()
				.name("userItemReader")
				.resource(new ClassPathResource("users.csv")).delimited().delimiter(",")
				.names(new String[] { "index", "userId", "firstName", "lastName","sex", "email","phone","dateofBirth","jobTitle" }).linesToSkip(1)
				.fieldSetMapper(new BeanWrapperFieldSetMapper<UserEntity>() {
					{
						setTargetType(UserEntity.class);
					}
				}).build();
	}
	
	@Bean
    public ItemProcessor<UserEntity, UserEntity> processor(){
      return user -> {
         return user;
      };
    }
	
	
	@Bean
    public ItemWriter<UserEntity> writer(){
       return users -> {
         System.out.println("Saving user Records: " +users);
         repository.saveAll(users);
       };
    }

}
