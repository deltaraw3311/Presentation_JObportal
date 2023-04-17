package com.hemant.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hemant.springboot.dto.AuthRequest;
import com.hemant.springboot.entities.Job;
import com.hemant.springboot.entities.UserInfo;
import com.hemant.springboot.services.JobService;
import com.hemant.springboot.services.JwtService;
import com.hemant.springboot.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
    private UserService userService;
	@Autowired
	private JwtService jwtService;
	@Autowired
    private AuthenticationManager authenticationManager;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/welcome")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String welcome()
    {
    	return "WELCOME TO THE JOB PORTAL";
    }
    @PostMapping("/register")
    public String registerUser(@RequestBody UserInfo user) {
    	
    	return userService.createUser(user);
     
    }
    @Autowired
    private JobService jobService;	

    @GetMapping("/jobs")
    public List<Job> searchJobs(@RequestParam("keywords") String keywords,
                                                 @RequestParam("location") String location) {
        List<Job> jobs = jobService.searchJobs(keywords, location);
        return jobs;
    }
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
