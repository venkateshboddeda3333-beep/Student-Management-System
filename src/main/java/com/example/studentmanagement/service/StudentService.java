package com.example.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    
    // GET ALL WITH PAGINATION AND SORTING
    public Page<Student> getAllStudentsWithPagination(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    // GET BY ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Student updateStudent(Long id, Student student) {

        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent != null) {

            existingStudent.setName(student.getName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setAge(student.getAge());
            existingStudent.setCourse(student.getCourse());

            return studentRepository.save(existingStudent);
        }

        return null;
    }

    // DELETE
    public boolean deleteStudent(Long id) {

        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }

        return false;
    }

    // PATCH
    public Student patchStudent(Long id, Student student) {

        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent != null) {

            if (student.getName() != null) {
                existingStudent.setName(student.getName());
            }

            if (student.getEmail() != null) {
                existingStudent.setEmail(student.getEmail());
            }

            if (student.getAge() != null) {
                existingStudent.setAge(student.getAge());
            }

            if (student.getCourse() != null) {
                existingStudent.setCourse(student.getCourse());
            }

            return studentRepository.save(existingStudent);
        }

        return null;
    }

    // PAGINATION + SORTING
    public Page<Student> getStudentsWithPagination(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}