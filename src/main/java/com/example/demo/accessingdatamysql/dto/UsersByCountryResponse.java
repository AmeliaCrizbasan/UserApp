package com.example.demo.accessingdatamysql.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UsersByCountryResponse {

    private String countryName;
    private Set<AddressResponse.UserSmallDetails> userSmallDetails = new HashSet<>();
}
