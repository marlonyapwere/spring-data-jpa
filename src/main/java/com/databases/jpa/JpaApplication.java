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

			Student sharon2 = new Student(
					"Sharon2",
					"N",
					"sharo@email.com",
					25);

			studentRepository.saveAll(List.of(marlon, sharon, sharon2));

			studentRepository
					.findStudentByEmail("sharon@email.com")
					.ifPresentOrElse(System.out::println, () -> {
						System.out.println("Student with specified email not found");
					});

			studentRepository.findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqualNative(
					"sharon",
					25
			).forEach(System.out::println);
		};
	}

}
