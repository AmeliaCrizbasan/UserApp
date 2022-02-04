package com.example.demo.accessingdatamysql.dto;

import com.example.demo.accessingdatamysql.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UsersByCountryDTO {

    private CountryResponse country;
    private Set<User> users = new HashSet<>();

}
