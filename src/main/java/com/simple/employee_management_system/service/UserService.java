package com.simple.employee_management_system.service;

import com.simple.employee_management_system.models.User;

public interface UserService {
void saveUser(User u);
User verifyUser(String username,String password);
}
