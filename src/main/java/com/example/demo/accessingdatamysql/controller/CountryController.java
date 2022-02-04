package com.example.demo.accessingdatamysql.controller;

import com.example.demo.accessingdatamysql.dto.CountryRequest;
import com.example.demo.accessingdatamysql.dto.CountryResponse;
import com.example.demo.accessingdatamysql.dto.UsersByCountryResponse;
import com.example.demo.accessingdatamysql.exception.CountryNotFoundException;
import com.example.demo.accessingdatamysql.exception.RecordNotFoundException;
import com.example.demo.accessingdatamysql.service.CountryService;
import com.example.demo.accessingdatamysql.service.UsersByCountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/countries")
public class CountryController {

    private final CountryService countryService;
    private final UsersByCountryService usersByCountryService;

    public CountryController(CountryService countryService, UsersByCountryService usersByCountryService) {
        this.countryService = countryService;
        this.usersByCountryService = usersByCountryService;
    }


    @PostMapping
    public ResponseEntity<CountryResponse> addNewCountry(@RequestBody CountryRequest request) {
        CountryResponse response = countryService.saveCountry(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCountry(@RequestBody CountryRequest request, @PathVariable Integer id) throws CountryNotFoundException {
        countryService.updateCountry(request, id);
    }

    @GetMapping
    public ResponseEntity<List<CountryResponse>> getAllCountries() {
        return new ResponseEntity<>(countryService.getAllCountries(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CountryResponse> getCountryById(@PathVariable Integer id) {
        CountryResponse countryResponse = countryService.getCountryById(id);
        if (countryResponse != null) {
            return new ResponseEntity<>(countryResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCountryId(@PathVariable("id") Integer id) throws RecordNotFoundException {
        countryService.deleteCountryById(id);
    }

    @GetMapping(path = "/getAllUsersByCountry")
    public ResponseEntity<List<UsersByCountryResponse>> getAllUsersByCountry() {
        return new ResponseEntity<>(usersByCountryService.getAllUsersByCountry(), HttpStatus.OK);
    }

}
