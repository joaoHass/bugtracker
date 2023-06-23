package com.hass.bugtracker.domain;

import com.hass.bugtracker.models.User;
import com.hass.bugtracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDomain {

    private final UserRepository _repository;

    @Autowired
    public UserDomain(UserRepository repository) { _repository = repository; }

    public List<User> getUsers() { return _repository.findAll(); }

    public Optional<User> getUserByEmail(String email) { return _repository.findUserByEmail(email); }

    public void save(User user) throws IllegalArgumentException{
        if (user.getEmail() == null || user.getEmail().isBlank())
            throw new IllegalArgumentException("The provided email is invalid");

        if (_repository.findUserByEmail(user.getEmail()).isPresent())
            throw new IllegalArgumentException(String.format("The e-mail %s is already taken", user.getEmail()));

        if (user.getUsername() == null || user.getUsername().isBlank())
            throw new IllegalArgumentException("The provided username is invalid");

        if (user.getPassword() == null || user.getPassword().isBlank())
            throw new IllegalArgumentException("The password can not be null, empty or only have blank characters");

        _repository.save(user);
    }

    public Optional<User> findById(Integer userId)  {
        return _repository.findById(userId);
    }
}
