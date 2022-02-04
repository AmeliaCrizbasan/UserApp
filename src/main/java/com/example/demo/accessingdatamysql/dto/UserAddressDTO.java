package com.example.demo.accessingdatamysql.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAddressDTO {

    private Integer id;
    private String streetName;
    private String city;
    private CountryDTO country;
    private UserResponse userResponse;
}
