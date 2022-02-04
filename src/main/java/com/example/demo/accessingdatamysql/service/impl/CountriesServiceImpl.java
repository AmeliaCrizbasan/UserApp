package com.example.demo.accessingdatamysql.service.impl;

import com.example.demo.accessingdatamysql.dto.CountryRequest;
import com.example.demo.accessingdatamysql.dto.CountryResponse;
import com.example.demo.accessingdatamysql.exception.CountryNotFoundException;
import com.example.demo.accessingdatamysql.exception.RecordNotFoundException;
import com.example.demo.accessingdatamysql.model.Country;
import com.example.demo.accessingdatamysql.repository.CountryRepository;
import com.example.demo.accessingdatamysql.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountriesServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountriesServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryResponse saveCountry(CountryRequest countryRequest) {
        Country country = new Country();
        country.setCountryName(countryRequest.getCountryName());

        Country response = countryRepository.save(country);

        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setId(response.getId());
        countryResponse.setCountryName(response.getCountryName());

        return countryResponse;
    }

    @Override
    public void updateCountry(CountryRequest countryRequest, Integer id) throws CountryNotFoundException {
        Country country = countryRepository.findById(id).orElseThrow(CountryNotFoundException::new);

        country.setCountryName(countryRequest.getCountryName());
        countryRepository.save(country);
    }

    @Override
    public List<CountryResponse> getAllCountries() {
        Iterable<Country> countries = countryRepository.findAll();
        List<CountryResponse> response = new ArrayList<>();
        for (Country c : countries) {
            CountryResponse countryResponse = new CountryResponse();
            countryResponse.setId(c.getId());
            countryResponse.setCountryName(c.getCountryName());
            response.add(countryResponse);
        }
        return response;
    }

    @Override
    public CountryResponse getCountryById(Integer id) {
        Optional<Country> countryOptional = countryRepository.findById(id);

        if (countryOptional.isPresent()) {

            Country country = countryOptional.get();
            CountryResponse countryResponse = new CountryResponse();
            countryResponse.setId(country.getId());
            countryResponse.setCountryName(country.getCountryName());

            return countryResponse;
        } else {
            return null;
        }
    }

    @Override
    public void deleteCountryById(Integer id) throws RecordNotFoundException {
        Optional<Country> country = countryRepository.findById(id);
        if (country.isPresent()) {
            countryRepository.delete(country.get());
        } else {
            throw new RecordNotFoundException();
        }

    }


}
