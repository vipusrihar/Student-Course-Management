package com.vipusa.management.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentUpdateRequest {

    private String title;

    private String name;

    private String address;

    private String city;

    private String courseId;
}
