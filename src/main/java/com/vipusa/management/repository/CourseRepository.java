package com.vipusa.management.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.vipusa.management.exception.FirebaseOperationException;
import com.vipusa.management.exception.ResourceNotFoundException;
import com.vipusa.management.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class CourseRepository {

    private static final String COLLECTION_NAME = "courses";
    private final Firestore firestore;

    public CourseRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public Course saveOrUpdate(Course course) {
        try {
            if (course.getId() == null || course.getId().isEmpty()) {
                // generate new ID for new course
                course.setId(firestore.collection(COLLECTION_NAME).document().getId());
            }
            firestore.collection(COLLECTION_NAME).document(course.getId()).set(course).get();
            return course;
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FirebaseOperationException("Failed to save or update course", e);
        }
    }

    public Course saveIfNotExists(Course course) {
        try {
            Query query = firestore.collection(COLLECTION_NAME)
                    .whereEqualTo("name", course.getName());
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            List<QueryDocumentSnapshot> docs = querySnapshot.get().getDocuments();

            if (!docs.isEmpty()) {
                // return existing
                return docs.get(0).toObject(Course.class);
            }

            // else create new
            return saveOrUpdate(course);
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FirebaseOperationException("Failed to save course", e);
        }
    }

    public Course findById(String id) {
        try {
            DocumentSnapshot doc = firestore.collection(COLLECTION_NAME).document(id).get().get();
            if (!doc.exists()) {
                throw new ResourceNotFoundException("Course not found with id: " + id);
            }
            return doc.toObject(Course.class);
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FirebaseOperationException("Failed to fetch course", e);
        }
    }

    public List<Course> findAll() {
        try {
            List<Course> courses = new ArrayList<>();
            List<QueryDocumentSnapshot> docs = firestore.collection(COLLECTION_NAME).get().get().getDocuments();
            for (DocumentSnapshot doc : docs) {
                courses.add(doc.toObject(Course.class));
            }
            return courses;
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FirebaseOperationException("Failed to fetch all courses", e);
        }
    }

    public void deleteById(String id) {
        try {
            firestore.collection(COLLECTION_NAME).document(id).delete().get();
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FirebaseOperationException("Failed to delete course", e);
        }
    }
}
