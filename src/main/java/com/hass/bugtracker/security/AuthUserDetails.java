package com.hass.bugtracker.security;

import com.hass.bugtracker.domain.user.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthUserDetails implements UserDetails {

    private final UserDomain _userDomain;

    private String email;

    @Autowired
    public AuthUserDetails(UserDomain userDomain) {
        _userDomain = userDomain;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    @Override
    public String getPassword() {
        return ((UserDetails)SecurityContextHolder
                                            .getContext()
                                            .getAuthentication()
                                            .getPrincipal())
                                            .getPassword();
    }

    @Override
    public String getUsername() {
        return ((UserDetails)SecurityContextHolder
                                            .getContext()
                                            .getAuthentication()
                                            .getCredentials())
                                            .getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return _userDomain.getUserByEmail(SecurityContextHolder.
                        getContext().
                        getAuthentication().
                        getName()).
                get().isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return _userDomain.getUserByEmail(SecurityContextHolder.
                        getContext().
                        getAuthentication().
                        getName()).
                get().isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return _userDomain.getUserByEmail(SecurityContextHolder.
                        getContext().
                        getAuthentication().
                        getName()).
                get().isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return _userDomain.getUserByEmail(SecurityContextHolder.
                        getContext().
                        getAuthentication().
                        getName()).
                get().isEnabled();
    }
}
