package com.mmd.hr.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {

	@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource){

		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

		jdbcUserDetailsManager
				.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?");

		jdbcUserDetailsManager
				.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id=?");

		return jdbcUserDetailsManager;
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

		httpSecurity.authorizeHttpRequests(configurer-> configurer

				.requestMatchers("/login", "/logout").permitAll()

				.requestMatchers(HttpMethod.GET, "/employees/").hasRole(Roles.EMPLOYEE.name())

				.requestMatchers(HttpMethod.GET, "/employees/showEmployeeForm", "/employees/showUpdateForm")
								.hasRole(Roles.MANAGER.name())

				.requestMatchers(HttpMethod.POST, "/employees/saveEmployee").hasRole(Roles.MANAGER.name())

				.requestMatchers(HttpMethod.DELETE, "/employees/delete").hasRole(Roles.ADMIN.name())

				.anyRequest().authenticated()


				).formLogin(Customizer.withDefaults())
				.logout(Customizer.withDefaults());

		return httpSecurity.build();
	}

	enum Roles{
		EMPLOYEE, MANAGER, ADMIN
	}

}


