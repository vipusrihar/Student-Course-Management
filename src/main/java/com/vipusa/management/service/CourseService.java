package com.vipusa.management.service;

import com.vipusa.management.model.Course;
import com.vipusa.management.request.CourseRequest;
import com.vipusa.management.request.CourseUpdateRequest;

import java.util.List;

public interface CourseService {

    public Course createCourse(CourseRequest request);

    public Course getCourseById(String id);

    public List<Course> getAllCourses();

    public Course updateCourse(String id, CourseUpdateRequest request);

    public void deleteCourse(String id);
}

