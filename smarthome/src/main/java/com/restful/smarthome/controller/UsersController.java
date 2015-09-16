package com.restful.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restful.smarthome.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UsersController {
    @Autowired
    private UserService service;
    
    @RequestMapping
    public String findAll(Model model) {
        model.addAttribute("users", service.findAll());
        return "users";
    }
}
