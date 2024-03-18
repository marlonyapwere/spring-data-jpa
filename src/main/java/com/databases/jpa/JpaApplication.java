package com.databases.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Student marlon = new Student(
					"Marlon",
					"N",
					"marlone@email.com",
					21);

			Student sharon = new Student(
					"Sharon",
					"N",
					"sharon@email.com",
					25);
			studentRepository.save(marlon);

			studentRepository.saveAll(List.of(marlon, sharon));

			studentRepository
					.findStudentByEmail("sharon@email.com")
					.ifPresentOrElse(System.out::println, () -> {
						System.out.println("Student with specified email not found");
					});

		};
	}

}
