package com.example.demo.accessingdatamysql.repository;

import com.example.demo.accessingdatamysql.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
}
