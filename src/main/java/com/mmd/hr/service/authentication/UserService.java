package com.mmd.hr.service.authentication;

import com.mmd.hr.entity.authentication.User;
import com.mmd.hr.reposiroty.authentication.RoleRepository;
import com.mmd.hr.reposiroty.authentication.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public void save(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setDateCreated(LocalDate.now());
		userRepository.save(user);
	}

	@Transactional
	public void updateUser(User user){
		userRepository.save(user);
	}

	@Transactional
	public void updateUserPassword(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Transactional
	public void delete(User user){
		userRepository.delete(user);
	}

	public List<User> findAllUsers(){
		return userRepository.findAll();
	}

	public User findUserByUsername(String userId){
		return userRepository.findById(userId).orElse(null);
	}

	public User findUserByUsernameWithRoles(String username){
		return userRepository.findUserByUsernameWithRoles(username);
	}

}
