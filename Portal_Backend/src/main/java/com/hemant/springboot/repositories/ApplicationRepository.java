package com.hemant.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hemant.springboot.entities.Application;
import com.hemant.springboot.entities.Job;
import com.hemant.springboot.entities.UserInfo;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByCandidate(UserInfo candidate);
    List<Application> findByJob(Job job);
    Application findByCandidateAndJob(UserInfo candidate, Job job);
}