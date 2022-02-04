package com.example.demo.accessingdatamysql.repository;

import com.example.demo.accessingdatamysql.model.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {
    @Query(value = "SELECT * FROM user_profile WHERE age>:age", nativeQuery = true)
    List<UserProfile> select(@Param("age") Integer age);
}
