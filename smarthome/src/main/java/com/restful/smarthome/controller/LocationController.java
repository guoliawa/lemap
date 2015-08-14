package com.restful.smarthome.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.restful.smarthome.domain.LocationInfo;
import com.restful.smarthome.domain.User;
import com.restful.smarthome.service.LocationInfoService;
import com.restful.smarthome.service.UserService;

@Controller
public class LocationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LocationInfoService infoservice;

    @Autowired
    private UserService userservice;

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("locations", infoservice.findAll());
        return "locations";
    }

    @RequestMapping(value = "/locations/user", method = RequestMethod.GET)
    public String findUserLocations(@RequestParam("id") String userid,
            Model model) {
        model.addAttribute("userid", userid);
        model.addAttribute("locations", infoservice.findByUserid(userid));

        logger.info("Getting locations from \"" + userid + "\"");
        return "userlocations";
    }
    
    @RequestMapping(value = "/usermap", method = RequestMethod.GET)
    public String viewUsermap(@RequestParam("id") String userid,  Model model) {
        model.addAttribute("userid", userid);
        model.addAttribute("positions", infoservice.findPositionByUserid(userid));

        logger.info("Getting locations from \"" + userid + "\"");
        return "usermap";
    }

    @RequestMapping(value = "/locations/insertone", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> insertOne(
            @RequestBody LocationInfo userlocationinfo) {
        infoservice.insert(userlocationinfo);
        userservice.insert(new User(userlocationinfo.getUserid()));
        return new ResponseEntity<String>("User "
                + " has insert location data successfully", new HttpHeaders(),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/locations/insertmany", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> insertMany(
            @RequestBody LocationInfo[] userlocationinfos) {
        return new ResponseEntity<String>("User " + " has insert location data successfully", new HttpHeaders(),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/locations/upload", method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestParam("id") String userid,
            @RequestParam("file") MultipartFile file) {
        String methodName = "upload";

        logger.info(LocationController.class + "." + methodName
                + " accepts " + file.getName() + ".\n" + "The userid is \""
                + userid + "\"");
        if (!file.isEmpty()) {
            try {
                logger.info("File is not empty, starting to process this file!");
                List<LocationInfo> locations = new ArrayList<LocationInfo>();
                InputStream inputStream = file.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream, "UTF8"));
                LocationInfo location;
                String line;
                ObjectMapper mapper = new ObjectMapper();
                User newUser = new User(userid);
                
                while ((line = bufferedReader.readLine()) != null) {
                    location = mapper.readValue(line, LocationInfo.class);
                    if (location.getUserid() == null) {
                        location.setUserid(newUser.getUserid());
                    }

                    locations.add(location);
                }

                if (!userservice.isDuplicatedUser(newUser)) {
                    logger.info("This is a new user." + "Adding \"" + userid
                            + "\"" + " to USERS smartHome.users.");
                    userservice.insert(newUser);
                }

                logger.info("Inserting locations from \"" + userid + "\""
                        + "\n");
                infoservice.insert(locations);
                logger.info("Complete inserted locations from \"" + userid
                        + "\"");

                return new ResponseEntity<String>(
                        userid + " successfully uploaded locations!",
                        new HttpHeaders(), HttpStatus.OK);
            } catch (Exception e) {
                logger.debug("The exception is " + e);

                return new ResponseEntity<String>(userid + " failed to upload locations!"
                        + " => " + e.getMessage(), new HttpHeaders(),
                        HttpStatus.OK);
            }
        } else {
            logger.info("The userid \"" + userid + "\""
                    + "uploaded an empty file!" + "\n");

            return new ResponseEntity<String>(userid + " uploaed an empty file.",
                    new HttpHeaders(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/locations/insertmany/userid", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> insertManyForUser(
            @RequestParam("id") String userid,
            @RequestBody LocationInfo[] userlocationinfos) {

        List<LocationInfo> locationList = new ArrayList<LocationInfo>();
        User newUser = new User(userid);

        for (LocationInfo location : userlocationinfos) {
            if (location.getUserid() == null) {
                location.setUserid(newUser.getUserid());
            }

            locationList.add(location);
        }

        if (!userservice.isDuplicatedUser(newUser)) {
            userservice.insert(newUser);
        }

        infoservice.insert(locationList);

        return new ResponseEntity<String>("User " + userid
                + " has insert location data successfully", new HttpHeaders(),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/locations/userid", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> insertLocationsTextUser(
            @RequestParam("id") String userid,
            @RequestBody LocationInfo[] userlocationinfos) {

        List<LocationInfo> locationList = new ArrayList<LocationInfo>();
        User newUser = new User(userid);

        for (LocationInfo location : userlocationinfos) {
            if (location.getUserid() == null) {
                location.setUserid(newUser.getUserid());
            }

            locationList.add(location);
        }

        if (!userservice.isDuplicatedUser(newUser)) {
            userservice.insert(newUser);
        }

        infoservice.insert(locationList);

        return new ResponseEntity<String>("User " + userid
                + " has insert location data successfully", new HttpHeaders(),
                HttpStatus.OK);
    }

}
