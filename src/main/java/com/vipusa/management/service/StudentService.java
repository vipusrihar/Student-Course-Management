package com.vipusa.management.service;

import com.vipusa.management.model.Student;
import com.vipusa.management.request.StudentRequest;
import com.vipusa.management.request.StudentUpdateRequest;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface StudentService {

    public Student createStudent(StudentRequest request);

    public Student getStudentById(String id);

    public List<Student> getAllStudents();

    public Student updateStudent(String id, StudentUpdateRequest request);

    public void deleteStudent(String id);


}