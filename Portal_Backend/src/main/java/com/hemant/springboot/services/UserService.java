package com.hemant.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hemant.springboot.entities.UserInfo;
import com.hemant.springboot.repositories.UserInfoRepository;

@Service
public class UserService {

	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public String createUser(UserInfo user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userInfoRepository.save(user);
		return "User added to the system";
		
	}
	

}
