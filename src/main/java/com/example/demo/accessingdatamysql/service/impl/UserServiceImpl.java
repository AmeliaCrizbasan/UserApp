package com.example.demo.accessingdatamysql.service.impl;

import com.example.demo.accessingdatamysql.dto.AddressResponse;
import com.example.demo.accessingdatamysql.dto.CreateUserRequest;
import com.example.demo.accessingdatamysql.dto.UserAddressDTO;
import com.example.demo.accessingdatamysql.dto.UserResponse;
import com.example.demo.accessingdatamysql.exception.RecordNotFoundException;
import com.example.demo.accessingdatamysql.model.User;
import com.example.demo.accessingdatamysql.model.UserAddress;
import com.example.demo.accessingdatamysql.repository.AddressRepository;
import com.example.demo.accessingdatamysql.repository.UserRepository;
import com.example.demo.accessingdatamysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    private Validator validator;

    UserServiceImpl(Validator validator) {
        this.validator = validator;
    }
/*    public UserServiceImpl() {
    }

    public UserServiceImpl(UserProfileRepository userProfileRepository, UserRepository userRepository, AddressRepository addressRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }*/

    @Override
    @Validated
    @Valid
    public UserResponse saveUser(CreateUserRequest createUserRequest) throws ConstraintViolationException {

        User user = new User();
        user.setName(createUserRequest.getName());
        user.setEmail(createUserRequest.getEmail());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Set<UserAddress> addresses = new HashSet<>();
        for (UserAddressDTO userAddressDto : createUserRequest.getUserAddress()) {
            UserAddress userAddress = new UserAddress();
            userAddress.setUser(user);
            userAddress.setStreetName(userAddressDto.getStreetName());
            userAddress.setCity(userAddressDto.getCity());
            userAddress.setCountry(userAddressDto.getCountry().toEntity());
            addresses.add(userAddress);
        }
        user.setUserAddress(addresses);
        User response = userRepository.save(user);

        return response.toDTO();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        List<User> output = new ArrayList<>();
        users.forEach(output::add);

        Collections.sort(output);
        List<UserResponse> response = new ArrayList<>();

        for (User u : output) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(u.getId());
            userResponse.setName(u.getName());
            userResponse.setEmail(u.getEmail());
            List<UserAddressDTO> userAddressDTOList = new ArrayList<>();
            for (UserAddress ua : u.getUserAddress()) {
                userAddressDTOList.add(ua.toDTO(false));
            }
            userResponse.setUserAddress(userAddressDTOList);
            if (u.getUserProfile() != null)
                userResponse.setUserProfile(u.getUserProfile().toDTO());
            response.add(userResponse);
        }
        return response;
    }

    @Override
    public UserResponse getUserById(Integer id) {

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {

            User user = userOptional.get();
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setName(user.getName());
            userResponse.setEmail(user.getEmail());
            List<UserAddressDTO> userAddressDTOList = user.getUserAddress().stream().map(userAddress -> userAddress.toDTO(false)).collect(Collectors.toList());
            userResponse.setUserAddress(userAddressDTOList);
            if (user.getUserProfile() != null)
                userResponse.setUserProfile(user.getUserProfile().toDTO());
            return userResponse;
        } else {
            return null;
        }
    }

    public List<AddressResponse> getAllAddresses() {

        Iterable<UserAddress> addresses = addressRepository.findAll();
        List<UserAddress> output = new ArrayList<>();
        addresses.forEach(output::add);
        Collections.sort(output);
        List<AddressResponse> responses = new ArrayList<>();
        for (UserAddress ua : output) {
            AddressResponse addressResponse = new AddressResponse();
            addressResponse.setStreetName(ua.getStreetName());
            addressResponse.setCity(ua.getCity());
            addressResponse.setCountry(ua.getCountry().toDTO());
            responses.add(addressResponse);
        }
        return responses;

    }

    public void deleteUserById(Integer id) throws RecordNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new RecordNotFoundException();
        }
    }


}
