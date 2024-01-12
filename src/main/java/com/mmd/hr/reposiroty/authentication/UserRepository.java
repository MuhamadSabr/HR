package com.mmd.hr.reposiroty.authentication;


import com.mmd.hr.entity.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
	User findUserByUsernameWithRoles(@Param("username") String username);
}
