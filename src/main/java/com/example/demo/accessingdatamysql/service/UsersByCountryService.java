package com.example.demo.accessingdatamysql.service;

import com.example.demo.accessingdatamysql.dto.UsersByCountryResponse;

import java.util.List;

public interface UsersByCountryService {
    List<UsersByCountryResponse> getAllUsersByCountry();
}
