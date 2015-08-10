package com.restful.smarthome.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.smarthome.domain.User;
import com.restful.smarthome.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public List<String> findAllUserid() {
        List<String> alluserid = new ArrayList<String>();
        for (User user : findAll()) {
            alluserid.add(user.getUserid());
        }

        return alluserid;
    }

    public List<User> findByUsername(String name) {
        return repository.findByUsername(name);
    }

    public List<User> findByUserid(String userid) {
        return repository.findByUserid(userid);
    }

    public boolean isDuplicatedUser(User user) {
        for (User existingUser : findAll()) {
            if (existingUser.getUserid().equals(user.getUserid())) {
                return true;
            }
        }
        return false;
    }
    
    public User insert(User user) {
        return repository.insert(user);
    }
}
