package com.example.demo.accessingdatamysql.model;

import com.example.demo.accessingdatamysql.dto.CountryDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String countryName;

    public CountryDTO toDTO() {

        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setId(id);
        countryDTO.setCountryName(countryName);

        return countryDTO;
    }
}
