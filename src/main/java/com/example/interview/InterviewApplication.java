package com.example.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class InterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewApplication.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "Hello test1";
	}

}
