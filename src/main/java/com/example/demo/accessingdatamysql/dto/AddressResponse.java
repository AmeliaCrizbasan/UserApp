package com.example.demo.accessingdatamysql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class AddressResponse {
    private String streetName;
    private String city;
    private CountryDTO country;

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UserSmallDetails {

        private String name;
        private String email;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserSmallDetails that = (UserSmallDetails) o;
            return name.equals(that.name) && email.equals(that.email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, email);
        }
    }
}
