package com.vipusa.management.model;

import lombok.*;
import org.springframework.cloud.gcp.data.firestore.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collectionName = "students")
public class Student {

    private String id;

    private String title;

    private String name;

    private String address;

    private String city;

    private String courseId;
}