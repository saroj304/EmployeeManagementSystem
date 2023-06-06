package com.simple.employee_management_system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.employee_management_system.models.contact;
import com.simple.employee_management_system.serviceImpl.EmployeeServiceImpl;

@Controller
public class ContactController {
	@Autowired
	EmployeeServiceImpl empservice;
	@GetMapping("contact")
	public String get_Contact() {
		return "contact_form";
	}

	@PostMapping("contactform")
	@ResponseBody
	public String send_email(@ModelAttribute contact contact) {
//		String name = req.getParameter("name");
//		String email = req.getParameter("email");
//		String sub = req.getParameter("subject");
//		System.err.println(name+""+email+""+sub);
		empservice.sendMail(contact);
		return "contact_success";
	}

}
