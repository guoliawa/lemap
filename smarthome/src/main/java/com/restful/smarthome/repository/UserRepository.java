package com.restful.smarthome.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.restful.smarthome.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
    public List<User> findAll();

    public List<User> findByUsername(String username);

    public List<User> findByUserid(String userid);
}
