package com.hass.bugtracker.services;

import com.hass.bugtracker.domain.UserDomain;
import com.hass.bugtracker.dto.NewUserDto;
import com.hass.bugtracker.dto.UserInfoDto;
import com.hass.bugtracker.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserDomain _domain;
    private final PasswordEncoder _encoder;

    @Autowired
    public UserService(UserDomain domain, PasswordEncoder encoder) {
        _domain = domain;
        _encoder = encoder;
    }

    public List<UserInfoDto> getUsers() {
        List<User> items = _domain.getUsers();
        List<UserInfoDto> itemsToReturn = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {

            UserInfoDto itemToReturn = new UserInfoDto(
                    items.get(i).getId(),
                    items.get(i).getUsername(),
                    items.get(i).getEmail()

            );

            itemsToReturn.add(itemToReturn);
        }

        return itemsToReturn;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return _domain.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s does not exist!", email)));
    }

    public void createUser(NewUserDto newUserDto) throws IllegalArgumentException {
        if (_domain.getUserByEmail(newUserDto.email()).isPresent())
            throw new IllegalArgumentException(String.format("The e-mail %s is already taken", newUserDto.email()));

        User user = new User();
        user.setUsername(newUserDto.username());
        user.setEmail(newUserDto.email());
        user.setPassword(_encoder.encode(newUserDto.password()));

        _domain.save(user);
    }

}
