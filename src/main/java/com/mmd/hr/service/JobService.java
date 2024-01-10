package com.mmd.hr.service;

import com.mmd.hr.dto.CountryAndJobDTO;
import com.mmd.hr.entity.Job;
import com.mmd.hr.reposiroty.JobRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobService {

    private JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> findAllJobs() {
        return jobRepository.findAll();
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
