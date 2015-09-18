package com.restful.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restful.smarthome.service.UserProfileService;

@Controller
@RequestMapping
public class UserProfileController {
    @Autowired
    private UserProfileService service;

    @RequestMapping(value = "/userprofiles/get/profile", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody String getProfileByID(@RequestParam("id") String userid) {
        return service.getDataByID(userid);
    }

    @RequestMapping(value = "/userprofiles_copy/get/profile", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody String getProfileCopyByID(
            @RequestParam("id") String userid) {
        return service.getDataCopyByID(userid);
    }

    @RequestMapping(value = "/userprofiles_copy/update/timepoints/{userid}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<String> updateTimepointsByID(
            @RequestParam("id") int id, @PathVariable("userid") String userid,
            @RequestParam("minutes") String min) {
        if (service.updateTimepointsByID(id, userid, min)) {
            return new ResponseEntity<String>(userid
                    + " successfully updated timepoints remindnums!",
                    new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<String>(userid
                + " failed updating timepoints remindnums!", new HttpHeaders(),
                HttpStatus.OK);
    }
}
