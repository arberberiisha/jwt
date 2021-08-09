package com.jwt.security;

import com.jwt.security.domain.Role;
import com.jwt.security.domain.User;
import com.jwt.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "Arber","arberberiisha", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Test","test", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Test2","test2", "1234", new ArrayList<>()));

			userService.addRoleToUser("arberberiisha","ROLE_USER");
			userService.addRoleToUser("test","ROLE_USER");
			userService.addRoleToUser("test2","ROLE_USER");
			userService.addRoleToUser("arberberiisha","ROLE_MANAGER");
			userService.addRoleToUser("arberberiisha","ROLE_ADMIN");
			userService.addRoleToUser("arberberiisha","ROLE_SUPER_ADMIN");
		};
	}

}
