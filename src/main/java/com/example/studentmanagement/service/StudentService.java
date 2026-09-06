package com.example.studentmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Student createStudent(Student student) {
        return repository.save(student);
    }

    // GET ALL STUDENTS
    public List<Student> getStudents() {
        return repository.findAll();
    }

    // GET STUDENT BY ID
    public Student getStudent(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("Student not found with id: " + id));
    }

    // PUT - COMPLETE UPDATE
    public Student updateStudent(Long id, Student updatedStudent) {

        Student student = getStudent(id);

        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setAge(updatedStudent.getAge());
        student.setCourse(updatedStudent.getCourse());

        return repository.save(student);
    }

    // PATCH - PARTIAL UPDATE
    public Student updateStudentPartially(Long id, Student updatedStudent) {

        Student student = getStudent(id);

        if (updatedStudent.getName() != null) {
            student.setName(updatedStudent.getName());
        }

        if (updatedStudent.getEmail() != null) {
            student.setEmail(updatedStudent.getEmail());
        }

        if (updatedStudent.getAge() != null) {
            student.setAge(updatedStudent.getAge());
        }

        if (updatedStudent.getCourse() != null) {
            student.setCourse(updatedStudent.getCourse());
        }

        return repository.save(student);
    }

    // DELETE
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}