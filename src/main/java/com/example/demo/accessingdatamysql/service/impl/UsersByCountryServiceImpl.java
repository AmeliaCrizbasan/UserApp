package com.example.demo.accessingdatamysql.service.impl;

import com.example.demo.accessingdatamysql.dto.AddressResponse;
import com.example.demo.accessingdatamysql.dto.UsersByCountryResponse;
import com.example.demo.accessingdatamysql.model.Country;
import com.example.demo.accessingdatamysql.model.User;
import com.example.demo.accessingdatamysql.model.UserAddress;
import com.example.demo.accessingdatamysql.repository.CountryRepository;
import com.example.demo.accessingdatamysql.repository.UserRepository;
import com.example.demo.accessingdatamysql.service.UsersByCountryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersByCountryServiceImpl implements UsersByCountryService {

    private final CountryRepository countryRepository;
    private final UserRepository userRepository;

    public UsersByCountryServiceImpl(CountryRepository countryRepository, UserRepository userRepository) {
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<UsersByCountryResponse> getAllUsersByCountry() {

        List<UsersByCountryResponse> responseUserByCountry = new ArrayList<>();

        Iterable<Country> countries = countryRepository.findAll();

        for (Country country : countries) {
            UsersByCountryResponse usersByCountryResponse = new UsersByCountryResponse();
            usersByCountryResponse.setCountryName(country.getCountryName());

            responseUserByCountry.add(usersByCountryResponse);
        }

        Iterable<User> users = userRepository.findAll();
        for (User u : users) {
            for (UserAddress userAddress : u.getUserAddress()) {
                AddressResponse.UserSmallDetails userSmallDetails = new AddressResponse.UserSmallDetails(u.getName(), u.getEmail());

                for (UsersByCountryResponse ucr : responseUserByCountry) {
                    if (ucr.getCountryName().equals(userAddress.getCountry().getCountryName())) {
                        ucr.getUserSmallDetails().add(userSmallDetails);
                    }
                }
            }
        }

        return responseUserByCountry;
    }
}
