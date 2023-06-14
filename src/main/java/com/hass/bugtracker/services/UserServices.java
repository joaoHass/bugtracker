package com.hass.bugtracker.services;

import com.hass.bugtracker.domain.UserDomain;
import com.hass.bugtracker.dto.UserDto;
import com.hass.bugtracker.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices implements UserDetailsService {

    private final UserDomain _domain;
    private final PasswordEncoder _encoder;

    @Autowired
    public UserServices(UserDomain domain, PasswordEncoder encoder) {
        _domain = domain;
        _encoder = encoder;
    }

    public List<User> getUsers() { return _domain.getUsers(); }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return _domain.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s does not exist!", email)));
    }

    public void createUser(UserDto userDto) throws IllegalArgumentException {
        if (_domain.getUserByEmail(userDto.email()).isPresent())
            throw new IllegalArgumentException(String.format("The e-mail %s is already taken", userDto.email()));

        User user = new User();
        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        user.setPassword(_encoder.encode(userDto.password()));

        _domain.save(user);
    }

}
