package com.naren.batch.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.naren.batch.model.Employee;
import com.naren.batch.model.EmployeeEntity;

import java.util.List;

/**
 * ItemWriter
 */
public class EmployeeWriter implements ItemWriter<EmployeeEntity> {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private EmployeeEntityRepository employeeRepository;

    /**
     * ItemWriter writes received data to destination.
     *
     * @param inputMessage
     * @throws Exception
     */
    @Override
    public void write(List<? extends EmployeeEntity> inputMessage) throws Exception {
//    	for (EmployeeEntity item : inputMessage) {
//            writer.println(item.toString());
            employeeRepository.saveAll(inputMessage);
//        }
    }
}
