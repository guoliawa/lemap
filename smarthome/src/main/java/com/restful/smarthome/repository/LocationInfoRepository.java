package com.restful.smarthome.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.restful.smarthome.domain.LocationInfo;

public interface LocationInfoRepository extends MongoRepository<LocationInfo, String> {
    public List<LocationInfo> findAll();
    public List<LocationInfo> findByUserid(String userid);
    public List<LocationInfo> findByUseridOrderByTimestampDesc(String userid);
}