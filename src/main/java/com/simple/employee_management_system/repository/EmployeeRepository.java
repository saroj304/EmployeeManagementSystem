package com.simple.employee_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simple.employee_management_system.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
