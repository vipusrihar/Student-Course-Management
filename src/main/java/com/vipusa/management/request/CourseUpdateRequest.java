package com.vipusa.management.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseUpdateRequest {

    private String name;

    private Double fee;

    private String lecturerId;

    private String lecturerName;

}
