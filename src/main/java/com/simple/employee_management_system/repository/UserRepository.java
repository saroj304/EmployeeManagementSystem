package com.simple.employee_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simple.employee_management_system.models.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	User findByUsernameAndPassword(String username,String password);
}
