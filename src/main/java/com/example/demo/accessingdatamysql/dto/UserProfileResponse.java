package com.example.demo.accessingdatamysql.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileResponse {
    private Integer id;
    private Boolean newsletter;
    private Integer age;
    private Date dateOfBirth;
    private String phoneNumber;
}
