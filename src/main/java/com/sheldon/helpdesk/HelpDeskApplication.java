package com.sheldon.helpdesk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sheldon.helpdesk.api.service.UserService;

@SpringBootApplication
public class HelpDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserService userService, PasswordEncoder passwordEncoder) {
		return args -> {
			initUsers(userService, passwordEncoder);
		};
	}

	private void initUsers(UserService userService, PasswordEncoder passwordEncoder) {

		//this.addAdminUser(userService, passwordEncoder);		
	}
//	
//	
//	private void addAdminUser(UserService userService, PasswordEncoder passwordEncoder) {
//		
//		User admin = new User();
//		admin.setEmail("admin@helpdesk.com");
//		admin.setPassword(passwordEncoder.encode("123456"));
//		admin.setProfile(ProfileEnum.ROLE_CUSTOMER);
//		
//		userService.createOrUpdate(admin);
//		
//	}
}
