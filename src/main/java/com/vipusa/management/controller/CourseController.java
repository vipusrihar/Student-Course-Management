package com.vipusa.management.controller;

import com.vipusa.management.model.Course;
import com.vipusa.management.request.CourseRequest;
import com.vipusa.management.response.ApiResponse;
import com.vipusa.management.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Course Management", description = "APIs for managing courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    @Operation(summary = "Create a new course")
    public ResponseEntity<ApiResponse<Course>> createCourse(@Valid @RequestBody CourseRequest request)
            throws ExecutionException, InterruptedException {
        Course course = courseService.createCourse(request);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .isSuccess(true)
                .message("Course created successfully")
                .response(course)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get course by ID")
    public ResponseEntity<ApiResponse<Course>> getCourse(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        Course course = courseService.getCourseById(id);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .isSuccess(true)
                .message("Course retrieved successfully")
                .response(course)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all courses")
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses()
            throws ExecutionException, InterruptedException {
        List<Course> courses = courseService.getAllCourses();
        ApiResponse<List<Course>> response = ApiResponse.<List<Course>>builder()
                .isSuccess(true)
                .message("Courses retrieved successfully")
                .response(courses)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update course")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable String id,
                                                            @Valid @RequestBody CourseRequest request)
            throws ExecutionException, InterruptedException {
        Course course = courseService.updateCourse(id, request);
        ApiResponse<Course> response = ApiResponse.<Course>builder()
                .isSuccess(true)
                .message("Course updated successfully")
                .response(course)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete course by ID")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        courseService.deleteCourse(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .isSuccess(true)
                .message("Course deleted successfully")
                .response(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
