package com.mmd.hr.reposiroty;

import com.mmd.hr.dto.CountryAndJobDTO;
import com.mmd.hr.dto.DepartmentDTO;
import com.mmd.hr.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, String> {

	@Query("SELECT j.id FROM Job j WHERE j.jobTitle = :jobTitle")
	String getJobIdByJobTitle(@Param("jobTitle")String jobTitle);

	@Query("SELECT j.jobTitle FROM Job j WHERE j.jobId = :jobId")
	String getJobTitleByJobId(@Param("jobId")String jobId);

	@Query("SELECT new com.mmd.hr.dto.CountryAndJobDTOImpl(jobId as key, jobTitle as value) FROM Job")
	List<CountryAndJobDTO> getJobIdAndJobTitle();

}
