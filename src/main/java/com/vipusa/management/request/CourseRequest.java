package com.vipusa.management.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CourseRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Fee is required")
    private Double fee;

    @NotBlank(message = "Lecturer ID is required")
    private String lecturerId;

    @NotBlank(message = "Lecturer name is required")
    private String lecturerName;
}
