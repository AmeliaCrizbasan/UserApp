package com.example.demo.accessingdatamysql.controller;

import com.example.demo.accessingdatamysql.dto.AddressResponse;
import com.example.demo.accessingdatamysql.dto.CreateUserRequest;
import com.example.demo.accessingdatamysql.dto.UserResponse;
import com.example.demo.accessingdatamysql.exception.RecordNotFoundException;
import com.example.demo.accessingdatamysql.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/demo")
@Validated
public class MainController {

    private final UserService userService;


    public MainController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<UserResponse> addNewUser(@Valid @RequestBody CreateUserRequest request) throws ConstraintViolationException {
        UserResponse response = userService.saveUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        UserResponse userResponse = userService.getUserById(id);
        if (userResponse != null) {
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(path = "/getAllAddresses")
    public ResponseEntity<List<AddressResponse>> getAllAddresses() {
        return new ResponseEntity<>(userService.getAllAddresses(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("id") Integer id) throws RecordNotFoundException {
        userService.deleteUserById(id);
    }


}
