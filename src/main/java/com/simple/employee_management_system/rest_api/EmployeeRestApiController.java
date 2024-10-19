package com.simple.employee_management_system.rest_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.simple.employee_management_system.models.Employee;
import com.simple.employee_management_system.repository.EmployeeRepository;

@RestController
public class EmployeeRestApiController {
	@Autowired
	EmployeeRepository emp;
@GetMapping("/rest/api/list")
	public List<Employee> getAll() {
		return  emp.findAll();
	}
}
