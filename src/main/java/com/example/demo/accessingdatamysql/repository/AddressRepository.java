package com.example.demo.accessingdatamysql.repository;

import com.example.demo.accessingdatamysql.model.UserAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<UserAddress, Integer> {
}
