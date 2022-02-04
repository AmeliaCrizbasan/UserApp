package com.example.demo.accessingdatamysql.model;

import com.example.demo.accessingdatamysql.validator.AgeConstraint;
import com.example.demo.accessingdatamysql.validator.ContactNumberConstraint;
import com.example.demo.accessingdatamysql.dto.UserProfileDTO;
import com.example.demo.accessingdatamysql.validator.NameConstraint;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean newsletter;
    @AgeConstraint
    private Integer age;
    private Date dateOfBirth;

    @ContactNumberConstraint
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    public UserProfileDTO toDTO() {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(id);
        userProfileDTO.setNewsletter(newsletter);
        userProfileDTO.setAge(age);
        userProfileDTO.setDateOfBirth(dateOfBirth);
        userProfileDTO.setPhoneNumber(phoneNumber);
        return userProfileDTO;
    }
}
