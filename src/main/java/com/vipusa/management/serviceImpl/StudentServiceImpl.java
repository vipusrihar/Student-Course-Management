package com.vipusa.management.serviceImpl;

import com.vipusa.management.exception.ResourceNotFoundException;
import com.vipusa.management.model.Student;
import com.vipusa.management.repository.StudentRepository;
import com.vipusa.management.request.StudentRequest;
import com.vipusa.management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(StudentRequest request) throws ExecutionException, InterruptedException {
        Student student = new Student();
        student.setTitle(request.getTitle());
        student.setName(request.getName());
        student.setAddress(request.getAddress());
        student.setCity(request.getCity());
        student.setCourseId(request.getCourseId());

        return studentRepository.save(student);
    }

    public Student getStudentById(String id) throws ExecutionException, InterruptedException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        return student;
    }

    public List<Student> getAllStudents() throws ExecutionException, InterruptedException {
        return studentRepository.findAll();
    }

    public Student updateStudent(String id, StudentRequest request) throws ExecutionException, InterruptedException {
        Student existingStudent = getStudentById(id);

        existingStudent.setTitle(request.getTitle());
        existingStudent.setName(request.getName());
        existingStudent.setAddress(request.getAddress());
        existingStudent.setCity(request.getCity());
        existingStudent.setCourseId(request.getCourseId());

        return studentRepository.update(existingStudent);
    }

    public void deleteStudent(String id) throws ExecutionException, InterruptedException {
        getStudentById(id);
        studentRepository.deleteById(id);
    }
}