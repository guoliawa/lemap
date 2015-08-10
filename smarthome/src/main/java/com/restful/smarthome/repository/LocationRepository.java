package com.restful.smarthome.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.restful.smarthome.domain.Location;

public interface LocationRepository extends MongoRepository<Location, String> {
    public List<Location> findAll();
    public List<Location> findByTimestampAfter(Date date);
    public List<Location> findByUsername(String name);
}