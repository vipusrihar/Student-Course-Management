package com.vipusa.management.serviceImpl;

import com.vipusa.management.exception.ResourceNotFoundException;
import com.vipusa.management.model.Course;
import com.vipusa.management.repository.CourseRepository;
import com.vipusa.management.request.CourseRequest;
import com.vipusa.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(CourseRequest request) {
        try {
            Course course = new Course();
            course.setName(request.getName());
            course.setFee(request.getFee());
            course.setLecturerId(request.getLecturerId());
            course.setLecturerName(request.getLecturerName());

            return courseRepository.save(course);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create course", e);
        }
    }

    @Override
    public Course getCourseById(String id) {
        try {
            Course course = courseRepository.findById(id);
            if (course == null) {
                throw new ResourceNotFoundException("Course not found with id: " + id);
            }
            return course;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get course", e);
        }
    }

    @Override
    public List<Course> getAllCourses() {
        try {
            return courseRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all courses", e);
        }
    }

    @Override
    public Course updateCourse(String id, CourseRequest request) {
        try {
            Course existingCourse = getCourseById(id);

            existingCourse.setName(request.getName());
            existingCourse.setFee(request.getFee());
            existingCourse.setLecturerId(request.getLecturerId());
            existingCourse.setLecturerName(request.getLecturerName());

            return courseRepository.update(existingCourse);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update course", e);
        }
    }

    @Override
    public void deleteCourse(String id) {
        try {
            getCourseById(id);
            courseRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete course", e);
        }
    }
}
