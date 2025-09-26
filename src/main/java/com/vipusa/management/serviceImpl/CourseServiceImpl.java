package com.vipusa.management.serviceImpl;

import com.vipusa.management.exception.ResourceNotFoundException;
import com.vipusa.management.model.Course;
import com.vipusa.management.repository.CourseRepository;
import com.vipusa.management.request.CourseRequest;
import com.vipusa.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(CourseRequest request) throws ExecutionException, InterruptedException {
        Course course = new Course();
        course.setName(request.getName());
        course.setFee(request.getFee());
        course.setLecturerId(request.getLecturerId());
        course.setLecturerName(request.getLecturerName());

        return courseRepository.save(course);
    }

    public Course getCourseById(String id) throws ExecutionException, InterruptedException {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        return course;
    }

    public List<Course> getAllCourses() throws ExecutionException, InterruptedException {
        return courseRepository.findAll();
    }

    public Course updateCourse(String id, CourseRequest request) throws ExecutionException, InterruptedException {
        Course existingCourse = getCourseById(id);

        existingCourse.setName(request.getName());
        existingCourse.setFee(request.getFee());
        existingCourse.setLecturerId(request.getLecturerId());
        existingCourse.setLecturerName(request.getLecturerName());

        return courseRepository.update(existingCourse);
    }

    public void deleteCourse(String id) throws ExecutionException, InterruptedException {
        // Check if course exists
        getCourseById(id);
        courseRepository.deleteById(id);
    }
}
