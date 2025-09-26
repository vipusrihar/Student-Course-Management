package com.vipusa.management.controller;

import com.vipusa.management.model.Course;
import com.vipusa.management.request.CourseRequest;
import com.vipusa.management.request.CourseUpdateRequest;
import com.vipusa.management.response.ApiResponse;
import com.vipusa.management.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<ApiResponse<Course>> createCourse(@Valid @RequestBody CourseRequest request) {
        Course course = courseService.createCourse(request);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .success(true)
                .message("Course created successfully")
                .response(course)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourse(@PathVariable String id) {
        Course course = courseService.getCourseById(id);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .success(true)
                .message("Course retrieved successfully")
                .response(course)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/courses")
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        ApiResponse<List<Course>> response = ApiResponse.<List<Course>>builder()
                .success(true)
                .message("Courses retrieved successfully")
                .response(courses)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable String id,
                                                            @Valid @RequestBody CourseUpdateRequest request) {
        Course course = courseService.updateCourse(id, request);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .success(true)
                .message("Course updated successfully")
                .response(course)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .message("Course deleted successfully")
                .response(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
