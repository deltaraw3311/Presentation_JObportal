package com.hemant.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hemant.springboot.entities.Job;
import com.hemant.springboot.repositories.JobRepository;

@Service
public class JobService {
	@Autowired
    private  JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> searchJobs(String location, String skill) {
        return jobRepository.findByLocationAndSkill(location, skill);
    }

    public Job getJobById(Integer id) {
        return jobRepository.findById(id).orElse(null);
    }
}
