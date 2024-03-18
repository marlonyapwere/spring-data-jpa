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

			System.out.println("Adding marlon and sharon");
			studentRepository.saveAll(List.of(marlon, sharon));

			System.out.println("Number of Students");
			System.out.println(studentRepository.count());

			System.out.println("Find Student 2 By ID");
			studentRepository
					.findById(2L)
					.ifPresentOrElse(System.out::println, () -> {
				System.out.println("Student with ID 2 not found");
			});

			System.out.println("Find Student 3 By ID");
			studentRepository
					.findById(3L)
					.ifPresentOrElse(System.out::println, () -> {
						System.out.println("Student with ID 2 not found");
					});

			System.out.println("Find all Students");
			List<Student> students = studentRepository.findAll();
			students.forEach(System.out::println);


			System.out.println("Delete Student by ID");
			studentRepository.deleteById(1L);
			System.out.println(studentRepository.count());
		};
	}

}
