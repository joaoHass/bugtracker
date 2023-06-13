package com.hass.bugtracker.services;

import com.hass.bugtracker.domain.UserDomain;
import com.hass.bugtracker.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices implements UserDetailsService {

    private final UserDomain _domain;

    @Autowired
    public UserServices(UserDomain domain) { _domain = domain; }

    public List<User> getUsers() { return _domain.getUsers(); }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return _domain.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s does not exist!", email)));
    }
}
