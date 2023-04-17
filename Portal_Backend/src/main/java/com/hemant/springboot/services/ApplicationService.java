package com.hemant.springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hemant.springboot.entities.Application;
import com.hemant.springboot.entities.Job;
import com.hemant.springboot.entities.UserInfo;
import com.hemant.springboot.repositories.ApplicationRepository;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }
    public List<Application> getApplicationsByCandidate(UserInfo candidate) {
        return applicationRepository.findByCandidate(candidate);
    }
    public List<Application> getApplicationsByJob(Job job) {
        return applicationRepository.findByJob(job);
    }
    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }
}

