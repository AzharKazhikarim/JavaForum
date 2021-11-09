package net.javaForum.javaForum;

import net.javaForum.javaForum.model.Role;
import net.javaForum.javaForum.model.User;
import net.javaForum.javaForum.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JavaForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaForumApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args->{
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));

			userService.createUser(new User(null,"azhar", "123456",new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
			userService.addRoleToUser("azhar", "ROLE_ADMIN");
			userService.createUser(new User(null,"nuna_saruna", "123456",new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
			userService.addRoleToUser("nuna_saruna", "ROLE_USER");

		};
	}
}
