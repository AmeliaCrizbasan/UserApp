package com.example.demo.accessingdatamysql.dto;

import com.example.demo.accessingdatamysql.model.UserProfile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Integer id;
    private String name;
    private String email;
    // private UserAddressDTO userAddressDTO;
    private UserProfile userProfile;
}
