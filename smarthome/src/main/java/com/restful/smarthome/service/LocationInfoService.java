package com.restful.smarthome.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restful.smarthome.domain.LocationInfo;
import com.restful.smarthome.domain.PositionInfo;
import com.restful.smarthome.repository.LocationInfoRepository;

@Service
public class LocationInfoService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LocationInfoRepository repository;

    public List<LocationInfo> findAll() {
        return repository.findAll();
    }

    public List<LocationInfo> findByUserid(String userid) {
        return repository.findByUseridOrderByTimestampDesc(userid);
    }

    public void insert(List<LocationInfo> locationInfos) {
        repository.insert(locationInfos);
    }

    public void insert(LocationInfo locationInfo) {
        repository.insert(locationInfo);
    }

    public String findPositionByUserid(String userid) {
        String PositionsJsonString = "";
        ObjectMapper mapper = new ObjectMapper();
        List<LocationInfo> locations = findByUserid(userid);
        List<PositionInfo> positions = new ArrayList<PositionInfo>();

        for (LocationInfo location : locations) {
            if (location.getLatitude() != 0 || location.getLongitude() != 0) {
                positions.add(location.getPositionInfo());
            }
        }

        try {
            PositionsJsonString = mapper.writeValueAsString(positions);
        } catch (JsonProcessingException e) {
            logger.debug("Processing positions failed!", e.toString());
        }

        return PositionsJsonString;
    }
    
    public List<LocationInfo> findByUseridAndTime(String userid, String start,
            String end) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date startDate = new Date(); 
        Date endDate = new Date();

        try {
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            startDate = formatter.parse(start);
            endDate = formatter.parse(end);

            // endDate should add 1 day to left the Between work.
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            cal.add(Calendar.DATE, 1);
            endDate = cal.getTime();
        } catch (ParseException e) {
            logger.debug("Parsing date failed!", e.toString());
        }

        return repository.findByUseridAndTimestampBetweenOrderByTimestampDesc(
                userid, startDate, endDate);
    }
}
