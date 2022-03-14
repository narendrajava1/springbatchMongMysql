package com.naren.batch.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naren.batch.model.EmployeeEntity;
@Repository
public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity,Integer>{

}
