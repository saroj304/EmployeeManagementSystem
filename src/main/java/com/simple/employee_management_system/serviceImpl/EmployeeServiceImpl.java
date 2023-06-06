package com.simple.employee_management_system.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.simple.employee_management_system.models.Employee;
import com.simple.employee_management_system.models.contact;
import com.simple.employee_management_system.repository.EmployeeRepository;
import com.simple.employee_management_system.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository emprepo;
	@Autowired
    JavaMailSender javamailsender;
	@Override
	public void saveEmployee(Employee emp) {
		emprepo.save(emp);
	}

	@Override
	public List<Employee> dispEmp() {
		return emprepo.findAll();
	}
   
	public Employee getEmp(int id) {
		return emprepo.getOne(id);
	}

	@Override
	public void deleteEmp(int id) {
		emprepo.deleteById(id);
		
	}

	@Override
	public void sendMail(contact message) {
	
		
			SimpleMailMessage simplemailmessage=new SimpleMailMessage();
			simplemailmessage.setFrom("sarojkhatiwada1999@gmail.com");
			simplemailmessage.setTo(message.getEmail());
			simplemailmessage.setText(message.getMessage());
			simplemailmessage.setSubject(message.getSubject());
			
			javamailsender.send(simplemailmessage);
			
			//to add attachment
	
	}

}
