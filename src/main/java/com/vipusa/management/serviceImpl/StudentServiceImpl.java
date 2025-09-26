package com.vipusa.management.serviceImpl;

import com.vipusa.management.model.Student;
import com.vipusa.management.repository.StudentRepository;
import com.vipusa.management.request.StudentRequest;
import com.vipusa.management.request.StudentUpdateRequest;
import com.vipusa.management.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(StudentRequest request) {
        Student student = new Student();
        student.setTitle(request.getTitle());
        student.setName(request.getName());
        student.setAddress(request.getAddress());
        student.setCity(request.getCity());
        student.setCourseId(request.getCourseId());

        return studentRepository.saveIfNotExists(student);
    }

    @Override
    public Student getStudentById(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(String id, StudentUpdateRequest request) {
        Student existingStudent = getStudentById(id);

        if (request.getTitle() != null) {
            existingStudent.setTitle(request.getTitle());
        }
        if (request.getName() != null) {
            existingStudent.setName(request.getName());
        }
        if (request.getAddress() != null) {
            existingStudent.setAddress(request.getAddress());
        }
        if (request.getCity() != null) {
            existingStudent.setCity(request.getCity());
        }
        if (request.getCourseId() != null) {
            existingStudent.setCourseId(request.getCourseId());
        }

        return studentRepository.saveOrUpdate(existingStudent);
    }

    @Override
    public void deleteStudent(String id) {
        getStudentById(id);
        studentRepository.deleteById(id);
    }
}
