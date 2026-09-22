package com.example.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import com.example.studentmanagement.dto.StudentDto;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.response.ResponseGlobal;
import com.example.studentmanagement.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // CREATE
    @PostMapping
    public ResponseEntity<ResponseGlobal<StudentDto>> createStudent(
            @Valid @RequestBody StudentDto studentDto) {

        try {

            StudentDto savedStudent =
                    studentService.createStudent(studentDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ResponseGlobal.onSuccess(
                            "Student created successfully",
                            savedStudent
                    )
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(
                            ResponseGlobal.onFailure(
                                    e.getMessage()
                            )
                    );
        }
    }

    // GET ALL WITH PAGINATION AND SORTING
    @GetMapping
    public ResponseEntity<ResponseGlobal<Page<Student>>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> students =
                studentService.getStudentWithPagination(pageable);

        return ResponseEntity.ok(
                ResponseGlobal.onSuccess(
                        "Students fetched successfully",
                        students
                )
        );
    }   

    // GET BY ID
    @GetMapping("/{getid}")
    public ResponseEntity<ResponseGlobal<Student>> getStudentById(
            @PathVariable Long getid) {

        Student student = studentService.getStudentById(getid);

        if (student != null) {
            return ResponseEntity.ok(
                    ResponseGlobal.onSuccess(
                            "Student fetched successfully",
                            student
                    )
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ResponseGlobal.onFailure(
                                "Student not found"
                        )
                );
    }
    // UPDATE
    @PutMapping("/{updateid}")
    public ResponseEntity<ResponseGlobal<Student>> updateStudent(
            @PathVariable Long updateid,
            @RequestBody Student student) {

        Student updatedStudent =
                studentService.updateStudent(updateid, student);

        if (updatedStudent != null) {
            return ResponseEntity.ok(
                    ResponseGlobal.onSuccess(
                            "Student updated successfully",
                            updatedStudent
                    )
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ResponseGlobal.onFailure(
                                "Student not found"
                        )
                );
    }
    // DELETE
    @DeleteMapping("/{deleteid}")
    public ResponseEntity<ResponseGlobal<String>> deleteStudent(
            @PathVariable Long deleteid) {

        boolean deleted = studentService.deleteStudent(deleteid);

        if (deleted) {
            return ResponseEntity.ok(
                    ResponseGlobal.onSuccess(
                            "Student deleted successfully",
                            null
                    )
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseGlobal.onFailure(
                        "Student not found"
                ));
    }
    // PATCH
    @PatchMapping("/{patchid}")
    public ResponseEntity<ResponseGlobal<Student>> patchStudent(
            @PathVariable Long patchid,
            @RequestBody Student student) {

        Student updatedStudent =
                studentService.patchStudent(patchid, student);

        if (updatedStudent != null) {
            return ResponseEntity.ok(
                    ResponseGlobal.onSuccess(
                            "Student patched successfully",
                            updatedStudent
                    )
            );
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ResponseGlobal.onFailure(
                                "Student not found"
                        )
                );
    }
  }
