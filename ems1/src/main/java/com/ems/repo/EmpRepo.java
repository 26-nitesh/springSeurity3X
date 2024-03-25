package com.ems.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.Employee;

public interface EmpRepo extends JpaRepository<Employee, Integer>{

}
