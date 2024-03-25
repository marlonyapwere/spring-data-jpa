package com.databases.jpa;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			StudentRepository studentRepository,
			StudentIdCardRepository studentIdCardRepository) {
		return args -> {
			Faker faker = new Faker();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@email.com", firstName, lastName);
			Student student = new Student(firstName,
					lastName,
					email,
					faker.number().numberBetween(17, 55));

			StudentIdCard studentIdCard = new StudentIdCard(
					student,
					"123456789");

			studentIdCardRepository.save(studentIdCard);
			studentIdCardRepository.findById(1L)
					.ifPresent(System.out::println);
		};

	}

	private void generateRandomStudents(StudentRepository studentRepository) {
			Faker faker = new Faker();

			for (int i = 0; i < 20; i++) {
				String firstName = faker.name().firstName();
				String lastName = faker.name().lastName();
				String email = String.format("%s.%s@email.com", firstName, lastName);
				Student student = new Student(firstName,
						lastName,
						email,
						faker.number().numberBetween(17, 55));
				studentRepository.save(student);
			}
	}

}
