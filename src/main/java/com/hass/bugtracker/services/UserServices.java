package com.hass.bugtracker.services;

import com.hass.bugtracker.domain.UserDomain;
import com.hass.bugtracker.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServices {

    private final UserDomain _domain;

    @Autowired
    public UserServices(UserDomain domain) { _domain = domain; }

    public List<User> getUsers() { return _domain.getUsers(); }
}
