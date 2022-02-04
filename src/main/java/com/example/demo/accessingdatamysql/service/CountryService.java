package com.example.demo.accessingdatamysql.service;

import com.example.demo.accessingdatamysql.dto.CountryRequest;
import com.example.demo.accessingdatamysql.dto.CountryResponse;
import com.example.demo.accessingdatamysql.exception.CountryNotFoundException;
import com.example.demo.accessingdatamysql.exception.RecordNotFoundException;

import java.util.List;

public interface CountryService {
    CountryResponse saveCountry(CountryRequest countryRequest);

    void updateCountry(CountryRequest countryRequest, Integer id) throws CountryNotFoundException;

    List<CountryResponse> getAllCountries();

    CountryResponse getCountryById(Integer id);

    void deleteCountryById(Integer id) throws RecordNotFoundException;

}
