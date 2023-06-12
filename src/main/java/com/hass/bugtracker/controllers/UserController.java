package com.hass.bugtracker.controllers;

import com.hass.bugtracker.models.User;
import com.hass.bugtracker.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserServices _userServices;

    @Autowired
    public UserController(UserServices userServices) { _userServices = userServices; }

    @GetMapping("/")
    public List<User> getUsers() { return _userServices.getUsers(); }

}
