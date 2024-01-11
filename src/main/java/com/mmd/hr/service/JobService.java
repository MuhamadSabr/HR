package com.mmd.hr.service;

import com.mmd.hr.dto.CountryAndJobDTO;
import com.mmd.hr.entity.Department;
import com.mmd.hr.entity.Job;
import com.mmd.hr.reposiroty.JobRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JobService {

    private JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Transactional
    public void save(Job job){
        jobRepository.save(job);
    }

    @Transactional
    public void delete(Job job){
        jobRepository.delete(job);
    }

    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    public Job findJobById(String jobId){
        return jobRepository.findById(jobId).orElse(null);
    }

    public String getJobIdByJobTitle(String jobTitle) {
        return jobRepository.getJobIdByJobTitle(jobTitle);
    }

    public String getJobTitleById(String jobId) {
        return jobRepository.getJobTitleByJobId(jobId);
    }

    public List<CountryAndJobDTO> getJobIdAndName() {
        return jobRepository.getJobIdAndJobTitle();
    }
}
