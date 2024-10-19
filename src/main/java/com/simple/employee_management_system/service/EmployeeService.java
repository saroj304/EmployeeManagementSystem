package com.simple.employee_management_system.service;

import java.util.List;
import java.util.Optional;

import com.simple.employee_management_system.models.Employee;
import com.simple.employee_management_system.models.contact;

public interface EmployeeService {
	// to save the employee
	void saveEmployee(Employee emp);

	// to display all the employees
	List<Employee> dispEmp();

	// to edit the employee
	Employee getEmp(int id);

	// to delete the employee
	void deleteEmp(int id);
	//employee contacting through email
	void sendMail(contact message);
}
