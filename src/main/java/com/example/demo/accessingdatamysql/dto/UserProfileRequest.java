package com.example.demo.accessingdatamysql.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserProfileRequest {

    private Boolean newsletter;
    private Integer age;
    private Date dateOfBirth;
    private String phoneNumber;
}
