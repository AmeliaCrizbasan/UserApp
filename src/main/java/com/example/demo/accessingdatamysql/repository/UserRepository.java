package com.example.demo.accessingdatamysql.repository;

import com.example.demo.accessingdatamysql.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
