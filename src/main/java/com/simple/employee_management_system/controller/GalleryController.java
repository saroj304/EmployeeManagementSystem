package com.simple.employee_management_system.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class GalleryController {
	@GetMapping("gallery")
	public String Gallery(Model model) {
		String img[]=new File("src/main/resources/static/images").list();
		System.out.println(img);
		model.addAttribute("image", img);
		return "gallery";
	}
}
