package com.example.demo.accessingdatamysql.service.impl;

import com.example.demo.accessingdatamysql.dto.UserProfileQuery;
import com.example.demo.accessingdatamysql.dto.UserProfileRequest;
import com.example.demo.accessingdatamysql.dto.UserProfileResponse;
import com.example.demo.accessingdatamysql.model.UserProfile;
import com.example.demo.accessingdatamysql.repository.UserProfileRepository;
import com.example.demo.accessingdatamysql.service.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfileResponse saveUserProfile(UserProfileRequest userProfileRequest) {
        UserProfile userProfile = new UserProfile();

        userProfile.setNewsletter(userProfileRequest.getNewsletter());

        userProfile.setAge(userProfileRequest.getAge());
        userProfile.setDateOfBirth(userProfileRequest.getDateOfBirth());

        userProfile.setPhoneNumber(userProfileRequest.getPhoneNumber());

        UserProfile response = userProfileRepository.save(userProfile);

        UserProfileResponse userProfileResponse = new UserProfileResponse();

        userProfileResponse.setId(response.getId());
        userProfileResponse.setNewsletter(response.getNewsletter());
        userProfileResponse.setPhoneNumber(response.getPhoneNumber());
        userProfileResponse.setAge(response.getAge());
        userProfileResponse.setDateOfBirth(response.getDateOfBirth());

        return userProfileResponse;

    }


    public List<UserProfileQuery> select(Integer age) {
        List<UserProfile> userProfile = userProfileRepository.select(age);
        List<UserProfileQuery> response = new ArrayList<>();
        for (UserProfile p : userProfile) {
            UserProfileQuery profileQuery = new UserProfileQuery();
            profileQuery.setId(p.getId());
            profileQuery.setAge(p.getAge());
            profileQuery.setNewsletter(p.getNewsletter());
            profileQuery.setDateOfBirth(p.getDateOfBirth());
            profileQuery.setPhoneNumber(p.getPhoneNumber());
            response.add(profileQuery);
        }
        return response;

    }
}

