package com.vipusa.management.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.vipusa.management.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class CourseRepository {

    private static final String COLLECTION_NAME = "courses";

    @Autowired
    private Firestore firestore;

    public Course save(Course course) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document();
        course.setId(docRef.getId());
        ApiFuture<WriteResult> result = docRef.set(course);
        result.get();
        return course;
    }

    public Course findById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(Course.class);
        }
        return null;
    }

    public List<Course> findAll() throws ExecutionException, InterruptedException {
        List<Course> courses = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = firestore.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        for (QueryDocumentSnapshot document : documents) {
            courses.add(document.toObject(Course.class));
        }
        return courses;
    }

    public Course update(Course course) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection(COLLECTION_NAME).document(course.getId());
        ApiFuture<WriteResult> result = docRef.set(course);
        result.get();
        return course;
    }

    public void deleteById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result = firestore.collection(COLLECTION_NAME).document(id).delete();
        result.get();
    }
}