package com.example.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.dto.StudentDto;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.exception.StudentNotFoundException;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;


    // CREATE
    public StudentDto createStudent(StudentDto studentDto) {

        if (studentRepository.existsByEmail(studentDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Student student = new Student();

        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setAge(studentDto.getAge());
        student.setCourse(studentDto.getCourse());

        Student savedStudent = studentRepository.save(student);

        StudentDto responseDto = new StudentDto();

        responseDto.setName(savedStudent.getName());
        responseDto.setEmail(savedStudent.getEmail());
        responseDto.setAge(savedStudent.getAge());
        responseDto.setCourse(savedStudent.getCourse());

        return responseDto;
    }


    // GET ALL WITH PAGINATION AND SORTING
    public Page<Student> getStudentWithPagination(Pageable pageable) {

        return studentRepository.findAll(pageable);
    }


    // GET BY ID
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                    new StudentNotFoundException("Student not found")
                );
    }

    // UPDATE
    public Student updateStudent(Long id, Student student) {

        Student existingStudent =
                studentRepository.findById(id).orElse(null);

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

        Student existingStudent =
                studentRepository.findById(id).orElse(null);

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
}