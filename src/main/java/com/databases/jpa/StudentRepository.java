package com.databases.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s from Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("select s from Student s where s.firstName = ?1 and s.age >= ?2")
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual(String firstName, Integer age);

    @Query(
            value = "SELECT * FROM student where first_name = ?1 AND age >= ?2",
            nativeQuery = true
    )
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqualNative(String firstName, Integer age);
    List<Student> findStudentByFirstNameEqualsAndAgeEquals(String firstName, int age);
}
