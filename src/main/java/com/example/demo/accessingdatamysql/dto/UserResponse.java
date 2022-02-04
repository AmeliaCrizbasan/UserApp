package com.example.demo.accessingdatamysql.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private Integer id;
    private String name;
    private String email;
    private List<UserAddressDTO> userAddress = new ArrayList<>();
    private UserProfileDTO userProfile;
}
