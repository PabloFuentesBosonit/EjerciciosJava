package com.example.block15springsecurity;

import com.example.block15springsecurity.domain.User;
import com.example.block15springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class Block15SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block15SpringSecurityApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@PostConstruct
	public void createUser(){
		User user1 = new User();
		user1.setName("John Travolta");
		user1.setUsername("john");
		user1.setEmail("john@.com");
		user1.setPassword(new BCryptPasswordEncoder().encode("1234"));

		userRepository.save(user1);

		User user2 = new User();
		user2.setName("Will Smith");
		user2.setUsername("will");
		user2.setEmail("will@.com");
		user2.setPassword(new BCryptPasswordEncoder().encode("1234"));

		userRepository.save(user2);

		User user3 = new User();
		user3.setName("Jim Carry");
		user3.setUsername("jim");
		user3.setEmail("jim@.com");
		user3.setPassword(new BCryptPasswordEncoder().encode("1234"));

		userRepository.save(user3);
	}

}


