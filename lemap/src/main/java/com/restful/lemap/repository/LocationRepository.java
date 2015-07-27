package com.restful.lemap.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.restful.lemap.domain.Location;

public interface LocationRepository extends MongoRepository<Location, String> {

    public List<Location> findAll();

}