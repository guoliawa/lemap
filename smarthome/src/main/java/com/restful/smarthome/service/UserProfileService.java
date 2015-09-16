package com.restful.smarthome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.smarthome.repository.UserProfileRepository;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository repository;
    
    public String getDataByID(String userid) {
        return repository.getDataByID(userid);
    }

    public String getDataCopyByID(String userid) {
        return repository.getDataCopyByID(userid);
    }
}
