package com.example.demo.accessingdatamysql.model;

import com.example.demo.accessingdatamysql.dto.UserAddressDTO;
import com.example.demo.accessingdatamysql.dto.UserResponse;
import com.example.demo.accessingdatamysql.validator.NameConstraint;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User implements Comparable<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NameConstraint
    private String name;
    @Email
    private String email;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private Set<UserAddress> userAddress = new HashSet<>();
    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private UserProfile userProfile;


    public UserResponse toDTO() {
        UserResponse userDTO = new UserResponse();
        userDTO.setId(id);
        userDTO.setName(name);
        userDTO.setEmail(email);
        List<UserAddressDTO> userAddressDTOList = new ArrayList<>();
        for (UserAddress ua : userAddress) {
            userAddressDTOList.add(ua.toDTO(false));
        }
        userDTO.setUserAddress(userAddressDTOList);
        //userDTO.setUserProfile(getUserProfile().toDTO());
        return userDTO;
    }

    @Override
    public int compareTo(User o) {
        return this.getName().compareTo(o.getName());
    }
}
