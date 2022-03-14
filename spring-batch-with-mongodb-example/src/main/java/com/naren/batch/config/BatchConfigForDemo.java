package com.naren.batch.config;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.naren.batch.model.Employee;
import com.naren.batch.model.EmployeeEntity;

@Configuration
@EnableBatchProcessing
public class BatchConfigForDemo {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private EmployeeEntityRepository employeeRepository;
	
	 @Bean
	  public Job readEmployee() throws Exception {
	    return jobBuilderFactory.get("readEmployee").flow(step1()).end().build();
	  }
	 
	 @Bean
	  public Step step1() throws Exception {
	    return stepBuilderFactory.get("step1").<Employee, EmployeeEntity>chunk(10).reader(reader()) .faultTolerant().skip(IOException.class)
	    		.processor(processor()) .faultTolerant().skip(IOException.class)
	        .writer(writer()) .faultTolerant().skip(IOException.class).build();
	  }

	 @Bean
	  public MongoItemReader<Employee> reader() {
	    MongoItemReader<Employee> reader = new MongoItemReader<>();
	    reader.setTemplate(mongoTemplate);
	    reader.setSort(new HashMap<String, Sort.Direction>() {{
	      put("_id", Direction.DESC);
	    }});
	    reader.setTargetType(Employee.class);
	    reader.setQuery("{}");
	    return reader;
	  }
	 
	 @Bean
	  public EmployeeProcessor processor() {
		 return new EmployeeProcessor();
	  }
	 @Bean
	 public EmployeeWriter writer() {
		 return new EmployeeWriter();
	 }


}
