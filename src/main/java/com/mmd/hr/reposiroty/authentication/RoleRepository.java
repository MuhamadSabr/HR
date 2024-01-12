package com.mmd.hr.reposiroty.authentication;

import com.mmd.hr.entity.authentication.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {

	@Query("SELECT r FROM Role r WHERE r.role in :names")
	List<Role> findRolesByNames(List<String> names);
}
