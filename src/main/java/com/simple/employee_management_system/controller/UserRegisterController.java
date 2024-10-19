package com.simple.employee_management_system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simple.employee_management_system.models.User;
import com.simple.employee_management_system.serviceImpl.UserServiceImpl;

@Controller
public class UserRegisterController {
	@Autowired
	UserServiceImpl ui;

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String signup(@ModelAttribute("userinfo") User userinfo) {
		return "register_page";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String save_User(@Valid @ModelAttribute("userinfo") User userinfo, BindingResult result, Model model) {
		System.out.println(result.getAllErrors());
		userinfo.setPassword(DigestUtils.md5DigestAsHex(userinfo.getPassword().getBytes()));
		// checking whether user already have account
		User data = ui.verifyUser(userinfo.getUsername(), userinfo.getPassword());
		if (data == null) {
			ui.saveUser(userinfo);
			return "login_page";
		}
		userinfo=null;
		model.addAttribute("error", "account already exists");
		return "login_page";
	}
}
