package com.vipusa.management.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.vipusa.management.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class StudentRepository {

    private static final String COLLECTION_NAME = "students";

    @Autowired
    private Firestore firestore;

    public Student save(Student student) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();
        student.setId(docRef.getId());
        ApiFuture<WriteResult> result = docRef.set(student);
        result.get();
        return student;
    }

    public Student findById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(Student.class);
        }
        return null;
    }

    public List<Student> findAll() throws ExecutionException, InterruptedException {
        List<Student> students = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            students.add(document.toObject(Student.class));
        }
        return students;
    }

    public Student update(Student student) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(student.getId());
        ApiFuture<WriteResult> result = docRef.set(student);
        result.get();
        return student;
    }

    public void deleteById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result = firestore.collection(COLLECTION_NAME).document(id).delete();
        result.get();
    }
}
