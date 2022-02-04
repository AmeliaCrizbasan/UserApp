package com.example.demo.accessingdatamysql.dto;

import com.example.demo.accessingdatamysql.model.Country;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDTO {
    private Integer id;
    private String countryName;

    public Country toEntity() {
        Country country = new Country();
        country.setId(id);
        country.setCountryName(countryName);
        return country;
    }
}
