package com.restful.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restful.smarthome.service.UserProfileService;
import com.restful.smarthome.service.UserService;

@Controller
@RequestMapping(value = "/userprofiles")
public class UserProfileController {
    @Autowired
    private UserProfileService service;
    
    @RequestMapping(value = "/get/profile", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody String getProfileByID(@RequestParam("id") String userid) {
        return service.getDataByID(userid);
    }
}
