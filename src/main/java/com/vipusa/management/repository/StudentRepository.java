package com.vipusa.management.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.vipusa.management.exception.FirebaseOperationException;
import com.vipusa.management.exception.ResourceAlreadyExistsException;
import com.vipusa.management.exception.ResourceNotFoundException;
import com.vipusa.management.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class StudentRepository {

    private static final String COLLECTION_NAME = "students";
    private final Firestore firestore;

    public StudentRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public Student saveIfNotExists(Student student) {
        try {
            Query query = firestore.collection(COLLECTION_NAME)
                    .whereEqualTo("name", student.getName())
                    .whereEqualTo("address", student.getAddress())
                    .whereEqualTo("city", student.getCity())
                    .whereEqualTo("courseId", student.getCourseId());
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            List<QueryDocumentSnapshot> docs = querySnapshot.get().getDocuments();

            if (!docs.isEmpty()) {
                throw new ResourceAlreadyExistsException(
                        "Student already exists with name: " + student.getName() +
                                ", address: " + student.getAddress() +
                                ", city: " + student.getCity() +
                                ", courseId: " + student.getCourseId()
                );
            }

            return saveOrUpdate(student);

        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FirebaseOperationException("Failed to save student", e);
        }
    }

    public Student saveOrUpdate(Student student) {
        try {
            if (student.getId() == null || student.getId().isEmpty()) {
                Query query = firestore.collection(COLLECTION_NAME)
                        .whereEqualTo("name", student.getName())
                        .whereEqualTo("address", student.getAddress())
                        .whereEqualTo("city", student.getCity())
                        .whereEqualTo("courseId", student.getCourseId());

                List<QueryDocumentSnapshot> docs = query.get().get().getDocuments();
                if (!docs.isEmpty()) {
                    throw new ResourceAlreadyExistsException(
                            "Student already exists with name: " + student.getName() +
                                    ", address: " + student.getAddress() +
                                    ", city: " + student.getCity() +
                                    ", courseId: " + student.getCourseId()
                    );
                }
                student.setId(firestore.collection(COLLECTION_NAME).document().getId());
            }

            firestore.collection(COLLECTION_NAME).document(student.getId()).set(student).get();
            return student;

        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FirebaseOperationException("Failed to save or update student", e);
        }
    }

    public Student findById(String id) {
        try {
            DocumentSnapshot doc = firestore.collection(COLLECTION_NAME).document(id).get().get();
            if (!doc.exists()) {
                throw new ResourceNotFoundException("Student not found with id: " + id);
            }
            return doc.toObject(Student.class);
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FirebaseOperationException("Failed to fetch student", e);
        }
    }

    public List<Student> findAll() {
        try {
            List<Student> students = new ArrayList<>();
            List<QueryDocumentSnapshot> docs = firestore.collection(COLLECTION_NAME).get().get().getDocuments();
            for (DocumentSnapshot doc : docs) {
                students.add(doc.toObject(Student.class));
            }
            return students;
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FirebaseOperationException("Failed to fetch all students", e);
        }
    }

    public void deleteById(String id) {
        try {
            firestore.collection(COLLECTION_NAME).document(id).delete().get();
        } catch (ExecutionException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FirebaseOperationException("Failed to delete student", e);
        }
    }
}
