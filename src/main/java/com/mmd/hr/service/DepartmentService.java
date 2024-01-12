package com.mmd.hr.service;

import com.mmd.hr.dto.department.DepartmentDTO;
import com.mmd.hr.entity.Department;
import com.mmd.hr.reposiroty.DepartmentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DepartmentService {

    private  DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public void save(Department department){
        departmentRepository.save(department);
    }

    @Transactional
    public void delete(Department department){
        departmentRepository.delete(department);
    }

    public Department findDepartmentById(int departmentId){
        return departmentRepository.findById(departmentId).orElse(null);
    }

    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }


    public List<DepartmentDTO> getDepartmentIdAndName() {
        return departmentRepository.getDepartmentIdAndDepartmentName();
    }

    public String getDepartmentNameById(int departmentId) {
        return departmentRepository.getDepartmentNameByDepartmentId(departmentId);
    }

    public Integer getDepartmentIdByName(String departmentName) {
        return departmentRepository.getDepartmentIdByDepartmentName(departmentName);
    }

    public Department findDepartmentByDepartmentIdWithAddress(int departmentId){
        return departmentRepository.findDepartmentByDepartmentIdWithAddress(departmentId);
    }
}
