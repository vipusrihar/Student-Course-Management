package com.vipusa.management.controller;

import com.vipusa.management.model.Student;
import com.vipusa.management.request.StudentRequest;
import com.vipusa.management.response.ApiResponse;
import com.vipusa.management.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Management", description = "APIs for managing students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @Operation(summary = "Create a new student")
    public ResponseEntity<ApiResponse<Student>> createStudent(@Valid @RequestBody StudentRequest request)
            throws ExecutionException, InterruptedException {
        Student student = studentService.createStudent(request);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .isSuccess(true)
                .message("Student created successfully")
                .response(student)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID")
    public ResponseEntity<ApiResponse<Student>> getStudent(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        Student student = studentService.getStudentById(id);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .isSuccess(true)
                .message("Student retrieved successfully")
                .response(student)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    @Operation(summary = "Get all students")
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents()
            throws ExecutionException, InterruptedException {
        List<Student> students = studentService.getAllStudents();
        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder()
                .isSuccess(true)
                .message("Students retrieved successfully")
                .response(students)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update student")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable String id,
                                                              @Valid @RequestBody StudentRequest request)
            throws ExecutionException, InterruptedException {
        Student student = studentService.updateStudent(id, request);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .isSuccess(true)
                .message("Student updated successfully")
                .response(student)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student by ID")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        studentService.deleteStudent(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .isSuccess(true)
                .message("Student deleted successfully")
                .response(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
