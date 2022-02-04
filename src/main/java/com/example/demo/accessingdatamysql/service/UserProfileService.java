package com.example.demo.accessingdatamysql.service;

import com.example.demo.accessingdatamysql.dto.UserProfileQuery;
import com.example.demo.accessingdatamysql.dto.UserProfileRequest;
import com.example.demo.accessingdatamysql.dto.UserProfileResponse;
import com.example.demo.accessingdatamysql.model.UserProfile;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface UserProfileService {
    UserProfileResponse saveUserProfile(UserProfileRequest request);

    List<UserProfileQuery> select(Integer age);
}
