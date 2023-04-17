package com.hemant.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hemant.springboot.entities.Application;
import com.hemant.springboot.entities.Job;
import com.hemant.springboot.entities.UserInfo;
import com.hemant.springboot.services.ApplicationService;
import com.hemant.springboot.services.JobService;

@RestController
@RequestMapping("/hello")
public class JobController {
	@Autowired
    private  JobService jobService;
	@Autowired
    private  ApplicationService applicationService;
	public JobController(JobService jobService, ApplicationService applicationService) {
        this.jobService = jobService;
        this.applicationService = applicationService;
    }
    
	
    @PostMapping("/jobs")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Job postJob(@RequestBody Job job) {
        Job savedJob = jobService.saveJob(job);
        return savedJob;
    }
   
    @GetMapping("/jobs/{id}/applications")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Application> getApplicationsByJob(@PathVariable Integer id) {
        Job job = jobService.getJobById(id);
        return applicationService.getApplicationsByJob(job);
    }
    @PostMapping("/jobs/{id}/apply")
    public Application applyForJob(@PathVariable Integer id, @RequestBody Application application) {
        Job job = jobService.getJobById(id);
        UserInfo candidate = null ;
        application.setCandidate(candidate);
        application.setJob(job);
        application.setStatus("applied");
        Application savedApplication = applicationService.saveApplication(application);
        return savedApplication;
    }



}
