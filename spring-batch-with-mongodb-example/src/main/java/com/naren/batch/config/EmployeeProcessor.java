package com.naren.batch.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;

import com.naren.batch.model.Employee;
import com.naren.batch.model.EmployeeEntity;

public class EmployeeProcessor implements ItemProcessor<Employee, EmployeeEntity> {

    /**
     * Read input data from itemReader, and then ItemProcessor applies the business logic here
     *
     * @param content
     * @return String
     * @throws Exception
     */
    @Override
    public EmployeeEntity process(Employee employee) throws Exception {
    	EmployeeEntity employeeEntity=new EmployeeEntity();
    	BeanUtils.copyProperties(employee, employeeEntity);
        return employeeEntity;
    }

}