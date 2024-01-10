package com.mmd.hr.reposiroty;

import com.mmd.hr.dto.DepartmentDTO;
import com.mmd.hr.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	@Query("SELECT d.id FROM Department d WHERE d.departmentName = :departmentName")
	Integer getDepartmentIdByDepartmentName(@Param("departmentName") String departmentName);

	@Query("SELECT d.departmentName FROM Department d WHERE d.departmentId = :departmentId")
	String getDepartmentNameByDepartmentId(@Param("departmentId") int departmentId);

	@Query("SELECT new com.mmd.hr.dto.DepartmentDTOImpl(departmentId as key, departmentName as value) FROM Department")
	List<DepartmentDTO> getDepartmentIdAndDepartmentName();
}
