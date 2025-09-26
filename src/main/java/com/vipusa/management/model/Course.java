package com.vipusa.management.model;

import lombok.*;
import org.springframework.cloud.gcp.data.firestore.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collectionName = "courses")
public class Course {

    private String id;

    private String name;

    private Double fee;

    private String lecturerId;

    private String lecturerName;

}
