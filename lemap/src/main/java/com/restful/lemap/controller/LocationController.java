package com.restful.lemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.restful.lemap.service.LocationService;

@Controller
public class LocationController {

    @Autowired
    private LocationService service;

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public String getPersonList(Model model) {
        model.addAttribute("locations", service.findAll());
        return "locations";
    }
}
