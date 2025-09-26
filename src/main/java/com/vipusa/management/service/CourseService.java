package com.vipusa.management.service;

import com.vipusa.management.model.Course;
import com.vipusa.management.request.CourseRequest;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CourseService {

    public Course createCourse(CourseRequest request) throws ExecutionException, InterruptedException ;

    public Course getCourseById(String id) throws ExecutionException, InterruptedException;
    public List<Course> getAllCourses() throws ExecutionException, InterruptedException;

    public Course updateCourse(String id, CourseRequest request) throws ExecutionException, InterruptedException;
    public void deleteCourse(String id) throws ExecutionException, InterruptedException;
}

