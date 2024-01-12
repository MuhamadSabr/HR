package com.mmd.hr.service.authentication;

import com.mmd.hr.entity.authentication.Role;
import com.mmd.hr.reposiroty.authentication.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleService {

	private RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public List<Role> findAllRoles(){
		return roleRepository.findAll();
	}

	public List<Role> findRolesByNames(List<String> names){
		return roleRepository.findRolesByNames(names);
	}
}
