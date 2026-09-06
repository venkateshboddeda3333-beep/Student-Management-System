package com.example.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentmanagement.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Spring Data JPA already provides save(), findById(), findAll(),
    // existsById(), deleteById(), delete(), count(), etc.

    // Example query-derivation methods (Module 16) you can uncomment/use later:
    // List<Student> findByName(String name);
    // List<Student> findByCourse(String course);
    // List<Student> findByAgeGreaterThan(int age);
}
