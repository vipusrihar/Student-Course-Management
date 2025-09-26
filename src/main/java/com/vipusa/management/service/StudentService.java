package com.vipusa.management.service;

import com.vipusa.management.model.Student;
import com.vipusa.management.request.StudentRequest;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface StudentService {

    public Student createStudent(StudentRequest request)
            throws ExecutionException, InterruptedException;

    public Student getStudentById(String id)
            throws ExecutionException, InterruptedException;

    public List<Student> getAllStudents()
            throws ExecutionException, InterruptedException ;

    public Student updateStudent(String id, StudentRequest request)
            throws ExecutionException, InterruptedException;

    public void deleteStudent(String id)
            throws ExecutionException, InterruptedException;


}