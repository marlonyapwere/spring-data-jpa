package com.databases.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s from Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("select s from Student s where s.firstName = ?1 and s.age >= ?2")
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual(String firstName, Integer age);

    @Query(
            value = "SELECT * FROM student where first_name = :firstName AND age >= :age",
            nativeQuery = true
    )
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqualNative(@Param("firstName") String firstName, @Param("age") Integer age);

    @Transactional
    @Modifying // This tell Spring Data that the query does not need to map anything from the database to an entity
    @Query("DELETE FROM Student u WHERE u.id = ?1")
    int deleteStudentById(Long id);
}
