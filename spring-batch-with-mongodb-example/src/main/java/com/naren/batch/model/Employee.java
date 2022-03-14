package com.naren.batch.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "employee")
public class Employee {
	@Id
	private Integer id;
	private String firstName;
	private String lastName;
	private String department;
	private BigDecimal salary;
}
