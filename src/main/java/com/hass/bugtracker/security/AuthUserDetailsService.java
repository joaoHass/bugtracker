package com.hass.bugtracker.security;

import com.hass.bugtracker.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final UserDomain _userDomain;

    @Autowired
    public AuthUserDetailsService(UserDomain userDomain) {
        _userDomain = userDomain;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return _userDomain.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s does not exist!", email)));
    }
}
