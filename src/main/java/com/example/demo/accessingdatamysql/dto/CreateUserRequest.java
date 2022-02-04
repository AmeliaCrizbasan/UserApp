package com.example.demo.accessingdatamysql.dto;

import com.example.demo.accessingdatamysql.model.UserProfile;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreateUserRequest {

    private String email;
    private String name;
    private Set<UserAddressDTO> userAddress = new HashSet<>();
    private UserProfile userProfile;

}
