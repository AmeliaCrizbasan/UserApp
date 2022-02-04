package com.example.demo.accessingdatamysql.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserProfileQuery {
    private Integer id;
    private Boolean newsletter;
    private Integer age;
    private Date dateOfBirth;
    private String phoneNumber;
}
