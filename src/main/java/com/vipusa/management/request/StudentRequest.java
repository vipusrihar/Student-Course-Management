package com.vipusa.management.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Name is required")
    private String name;

    private String address;
    private String city;

    @NotBlank(message = "Course ID is required")
    private String courseId;
}
