package com.simple.employee_management_system.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simple.employee_management_system.models.Employee;
import com.simple.employee_management_system.serviceImpl.EmployeeServiceImpl;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeServiceImpl empimpl;

	@RequestMapping(value = "employee_form", method = RequestMethod.GET)
	public String employeeDetail(@ModelAttribute("employee") Employee employee, HttpSession session, Model model) {
		if (session.getAttribute("userdata") != null) {
			return "employee_form";
		}
		return "login_page";

	}

	@RequestMapping(value = "saveemp", method = RequestMethod.POST)
	public String register_employee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result,
			Model model) {
////if the employee fields is not filled 
//		System.out.println(employee.getFname());
//		System.out.println(employee.getLname());
//		System.out.println(employee.getCompany());
//		System.out.println(employee.getEmail());
//		System.out.println(employee.getGender());
//		System.out.println(employee.getPhone());
//		System.out.println(employee.getPost());
//		System.out.println(employee.getSalary());

		if (result.hasErrors() == true) {
			model.addAttribute("error", "**please fill all the fields");
			return "employee_form";
		}
		empimpl.saveEmployee(employee);
		return "landing_page";
	}

	@GetMapping("employee_list")
	public String displayEmployee(Model model, HttpSession session) {

		if (session.getAttribute("userdata") != null) {
			List<Employee> emp = empimpl.dispEmp();
			model.addAttribute("emp_obj", emp);
			return "employee_list";
		}
		return "login_page";
	}

	@GetMapping("edit/{id}")
	public String editEmployee(@PathVariable int id, Model model) {

		model.addAttribute("employee", empimpl.getEmp(id));
		return "edit_employee_form";
	}

	@PostMapping("editemp")
	public String updateEmp(@ModelAttribute("employee") Employee employee) {
		empimpl.saveEmployee(employee);
		return "redirect:/employee_list";
	}

	@GetMapping("delete/{id}")
	public String deleteEmp(@PathVariable int id) {
		empimpl.deleteEmp(id);
		return "redirect:/employee_list";
	}

}
