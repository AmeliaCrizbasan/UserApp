package com.example.demo.accessingdatamysql.model;

import com.example.demo.accessingdatamysql.dto.UserAddressDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserAddress implements Comparable<UserAddress> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String streetName;
    private String city;
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    public UserAddressDTO toDTO(boolean includeUser) {

        UserAddressDTO userAddressDTO = new UserAddressDTO();
        userAddressDTO.setId(id);
        userAddressDTO.setStreetName(streetName);
        userAddressDTO.setCity(city);
        userAddressDTO.setCountry(country.toDTO());
        if (includeUser)
            userAddressDTO.setUserResponse(user.toDTO());

        return userAddressDTO;
    }

    @Override
    public int compareTo(UserAddress o) {
        return this.getStreetName().compareTo(o.getStreetName());
    }
}
