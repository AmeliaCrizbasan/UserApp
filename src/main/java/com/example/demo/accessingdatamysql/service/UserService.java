package com.example.demo.accessingdatamysql.service;

import com.example.demo.accessingdatamysql.dto.AddressResponse;
import com.example.demo.accessingdatamysql.dto.CreateUserRequest;
import com.example.demo.accessingdatamysql.dto.UserResponse;
import com.example.demo.accessingdatamysql.exception.RecordNotFoundException;

import java.util.List;

public interface UserService {

    UserResponse saveUser(CreateUserRequest createUserRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Integer id);

    List<AddressResponse> getAllAddresses();

    void deleteUserById(Integer id) throws RecordNotFoundException;

}
