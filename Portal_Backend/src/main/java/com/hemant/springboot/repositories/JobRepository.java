package com.hemant.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hemant.springboot.entities.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByLocationAndRequiredSkillsContains(String location, String requiredSkills);

	List<Job> findByLocationAndSkill(String location, String skill);
}