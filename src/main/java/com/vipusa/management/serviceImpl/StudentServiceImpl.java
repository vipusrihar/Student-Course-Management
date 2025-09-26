package com.vipusa.management.serviceImpl;

import com.vipusa.management.exception.ResourceNotFoundException;
import com.vipusa.management.model.Student;
import com.vipusa.management.repository.StudentRepository;
import com.vipusa.management.request.StudentRequest;
import com.vipusa.management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(StudentRequest request) {
        try {
            Student student = new Student();
            student.setTitle(request.getTitle());
            student.setName(request.getName());
            student.setAddress(request.getAddress());
            student.setCity(request.getCity());
            student.setCourseId(request.getCourseId());

            return studentRepository.save(student);
        } catch (Exception e) { // catch ExecutionException / InterruptedException here
            throw new RuntimeException("Failed to create student", e);
        }
    }

    @Override
    public Student getStudentById(String id) {
        try {
            Student student = studentRepository.findById(id);
            if (student == null) {
                throw new ResourceNotFoundException("Student not found with id: " + id);
            }
            return student;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get student", e);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            return studentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get all students", e);
        }
    }

    @Override
    public Student updateStudent(String id, StudentRequest request) {
        try {
            Student existingStudent = getStudentById(id);

            existingStudent.setTitle(request.getTitle());
            existingStudent.setName(request.getName());
            existingStudent.setAddress(request.getAddress());
            existingStudent.setCity(request.getCity());
            existingStudent.setCourseId(request.getCourseId());

            return studentRepository.update(existingStudent);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update student", e);
        }
    }

    @Override
    public void deleteStudent(String id) {
        try {
            getStudentById(id); // will throw ResourceNotFoundException if not found
            studentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete student", e);
        }
    }
}
