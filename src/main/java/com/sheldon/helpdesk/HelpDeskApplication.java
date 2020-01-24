package com.sheldon.helpdesk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;

import com.sheldon.helpdesk.api.entity.User;
import com.sheldon.helpdesk.api.enums.ProfileEnum;
import com.sheldon.helpdesk.api.service.UserService;

@SpringBootApplication
public class HelpDeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserService userService) {
		return args -> {
			initUsers(userService);
		};
	}

	private void initUsers(UserService userService) {

		Page<User> users = userService.findAll(1, 10);
		
		System.out.println(users);
	}
	
	
	private void addUser(UserService userService) {
		
		User admin = new User();
		admin.setEmail("custumer@helpdesk.com");
		admin.setPassword("123456");
		admin.setProfile(ProfileEnum.ROLE_CUSTOMER);
		
		userService.createOrUpdate(admin);
		
	}
}
