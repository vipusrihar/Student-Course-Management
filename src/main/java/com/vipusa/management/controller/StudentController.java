package com.vipusa.management.controller;

import com.vipusa.management.model.Student;
import com.vipusa.management.request.StudentRequest;
import com.vipusa.management.response.ApiResponse;
import com.vipusa.management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<ApiResponse<Student>> createStudent(@Valid @RequestBody StudentRequest request){
        Student student = studentService.createStudent(request);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .success(true)
                .message("Student created successfully")
                .response(student)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudent(@PathVariable String id) {
        Student student = studentService.getStudentById(id);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .success(true)
                .message("Student retrieved successfully")
                .response(student)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/students")
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        ApiResponse<List<Student>> response = ApiResponse.<List<Student>>builder()
                .success(true)
                .message("Students retrieved successfully")
                .response(students)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable String id,
                                                              @Valid @RequestBody StudentRequest request) {
        Student student = studentService.updateStudent(id, request);
        ApiResponse<Student> response = ApiResponse.<Student>builder()
                .success(true)
                .message("Student updated successfully")
                .response(student)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("Student deleted successfully")
                .response(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
