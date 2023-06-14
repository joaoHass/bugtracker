package com.hass.bugtracker.controllers;

import com.hass.bugtracker.dto.UserDto;
import com.hass.bugtracker.models.User;
import com.hass.bugtracker.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserServices _userServices;

    @Autowired
    public UserController(UserServices userServices) { _userServices = userServices; }

    @GetMapping("/")
    public List<User> getUsers() { return _userServices.getUsers(); }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        try {
            _userServices.createUser(userDto);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(String.format("User %s was created successfully!", userDto.username()), HttpStatus.OK);
    }

}
