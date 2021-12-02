package com.crud;

// import java.util.ArrayList;

// import com.crud.entity.Role;
// import com.crud.entity.User;
// import com.crud.enumeration.Gender;
// import com.crud.service.UserService;

// import java.util.ArrayList;

// import com.crud.entity.Role;
// import com.crud.entity.User;
// import com.crud.enumeration.Gender;
// import com.crud.service.UserService;

import org.modelmapper.ModelMapper;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Bean
	// CommandLineRunner run(UserService userService) {
	// 	return args -> {
	// 		userService.saveRole(new Role(null, "ROLE_USER"));
	// 		userService.saveRole(new Role(null, "ROLE_MANAGER"));
	// 		userService.saveRole(new Role(null, "ROLE_ADMIN"));
	// 		userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

	// 		userService.saveUser(
	// 			new User(
	// 				null,
	// 				"nazor",
	// 				"1234",
	// 				"nopalnaxcpm@gmail.com",
	// 				"08123",
	// 				Gender.MAN,
	// 				"Munjul",
	// 				new ArrayList<>()
	// 			)
	// 		);
	// 		userService.saveUser(
	// 			new User(
	// 				null,
	// 				"cwk",
	// 				"1234111",
	// 				"cwk@gmail.com",
	// 				"08125663",
	// 				Gender.WOMAN,
	// 				"cpy",
	// 				new ArrayList<>()
	// 			)
	// 		);

	// 		userService.addUserToRole("nopalnaxcpm@gmail.com", "ROLE_ADMIN");
	// 		userService.addUserToRole("cwk@gmail.com", "ROLE_USER");
	// 	};
	// }
}
