package com.hass.bugtracker.domain;

import com.hass.bugtracker.models.User;
import com.hass.bugtracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDomain {

    private final UserRepository _repository;

    @Autowired
    public UserDomain(UserRepository repository) { _repository = repository; }

    public List<User> getUsers() { return _repository.findAll(); }
}