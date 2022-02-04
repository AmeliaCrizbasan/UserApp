package com.example.demo.accessingdatamysql.controller;

import com.example.demo.accessingdatamysql.dto.UserProfileQuery;
import com.example.demo.accessingdatamysql.dto.UserProfileRequest;
import com.example.demo.accessingdatamysql.dto.UserProfileResponse;
import com.example.demo.accessingdatamysql.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/profile")
@Validated
public class ProfileController {

    private final UserProfileService profileService;

    public ProfileController(UserProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping(path = "addProfile")
    public ResponseEntity<UserProfileResponse> addNewProfile(@Valid @RequestBody UserProfileRequest request) {
        UserProfileResponse response = profileService.saveUserProfile(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/select/{age}")
    public ResponseEntity<List<UserProfileQuery>> select(@PathVariable Integer age) {
        return new ResponseEntity<>(profileService.select(age), HttpStatus.OK);
    }
}
