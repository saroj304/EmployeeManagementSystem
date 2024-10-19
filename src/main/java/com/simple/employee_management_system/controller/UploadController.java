package com.simple.employee_management_system.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	@GetMapping("upload")
	public String disp_image(HttpSession session) {
		if(session.getAttribute("userdata")!=null) {
			return "upload";
		}
		return"login_page";
	}

	@PostMapping("upload")
	public String upload_image(@RequestParam("file") MultipartFile image) throws IOException {
		if (!image.isEmpty()) {
			// save the image under the folder path with original name
			FileOutputStream fout = new FileOutputStream(
					"src/main/resources/static/images/" + image.getOriginalFilename());
			fout.write(image.getBytes());
			fout.close();
			return "redirect:/gallery";
		}
		return "upload";
	}
}
