package com.vipusa.management.serviceImpl;

import com.vipusa.management.model.Course;
import com.vipusa.management.repository.CourseRepository;
import com.vipusa.management.request.CourseRequest;
import com.vipusa.management.request.CourseUpdateRequest;
import com.vipusa.management.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(CourseRequest request) {
        Course course = new Course();
        course.setName(request.getName());
        course.setFee(request.getFee());
        course.setLecturerId(request.getLecturerId());
        course.setLecturerName(request.getLecturerName());
        return courseRepository.saveIfNotExists(course);
    }

    @Override
    public Course getCourseById(String id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(String id, CourseUpdateRequest request) {
        Course course = getCourseById(id);

        if (request.getName() != null && !request.getName().isBlank()) {
            course.setName(request.getName());
        }

        if (request.getFee() != null) {
            course.setFee(request.getFee());
        }

        if (request.getLecturerName() != null && !request.getLecturerName().isBlank()) {
            course.setLecturerName(request.getLecturerName());
        }

        if (request.getLecturerId() != null && !request.getLecturerId().isBlank()) {
            course.setLecturerId(request.getLecturerId());
        }

        return courseRepository.saveOrUpdate(course);
    }

    @Override
    public void deleteCourse(String id) {
        getCourseById(id);
        courseRepository.deleteById(id);
    }
}
