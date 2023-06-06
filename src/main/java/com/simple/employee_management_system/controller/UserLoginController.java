package com.simple.employee_management_system.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simple.employee_management_system.models.User;
import com.simple.employee_management_system.serviceImpl.UserServiceImpl;
import com.simple.employee_management_system.utilities.VerifyRecaptcha;

@Controller
public class UserLoginController {
	@Autowired
	UserServiceImpl ui;
	public static Logger log = LoggerFactory.getLogger("UserLoginController");

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		log.warn("hello iam inside login page");
		return "login_page";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String check_User(@ModelAttribute User u, Model model, HttpSession session, HttpServletRequest req)
			throws IOException {
//	checking if the bindined object have null username and password
		String check = req.getParameter("g-recaptcha-response");
		Boolean verify = VerifyRecaptcha.verify(check);
		if (verify) {
			if (u.getPassword().isEmpty() == false && u.getUsername().isEmpty() == false) {
				// encrypting the password of user
				u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
				// getting result of authorize user
				User user = ui.verifyUser(u.getUsername(), u.getPassword());

				if (user != null) {
//				System.println(user.getUsername());
					// setting authorized user oject to session to access everywhere through the
					// project
					session.setAttribute("userdata", user);
					session.setMaxInactiveInterval(60);
					model.addAttribute("data", user.getUsername());
					return "landing_page";
				} else {
					model.addAttribute("error", "User does not exists!!");
					return "login_page";
				}
			}
		}
		model.addAttribute("error", "you are not a human");
		return "login_page";
	}

	@GetMapping("logout")
	public String session_expire(Model model, HttpSession session) {
		session.invalidate();
		model.addAttribute("error", "logout successful");
		return "login_page";
	}

	@GetMapping("profile")
	public String user_profile(HttpSession session) {
		if (session.getAttribute("userdata") != null) {
			return "user_profile";
		}
		return "login_page";
	}
}
