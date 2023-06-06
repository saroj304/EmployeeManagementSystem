package com.simple.employee_management_system.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.employee_management_system.models.User;
import com.simple.employee_management_system.repository.UserRepository;
import com.simple.employee_management_system.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
UserRepository ur;

	public void saveUser(User u) {
		ur.save(u);
		
	}

	@Override
	public User verifyUser(String username, String password) {
		
		return ur.findByUsernameAndPassword(username, password);
	}

}
