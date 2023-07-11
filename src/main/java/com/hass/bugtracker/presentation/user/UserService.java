package com.hass.bugtracker.presentation.user;

import com.hass.bugtracker.domain.user.UserDomain;
import com.hass.bugtracker.domain.user.User;
import com.hass.bugtracker.presentation.user.dto.NewUserDto;
import com.hass.bugtracker.presentation.user.dto.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

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

    public void deleteUser(Integer userId) {
        _domain.delete(userId);
    }

    public void createUser(NewUserDto newUserDto) throws IllegalArgumentException {
        User user = new User();
        user.setUsername(newUserDto.username());
        user.setEmail(newUserDto.email());
        user.setPassword(_encoder.encode(newUserDto.password()));

        _domain.save(user);
    }

    public UserInfoDto findById(Integer userId) throws NoSuchElementException {
        Optional<User> user = _domain.findById(userId);

        if (user.isEmpty())
            throw new NoSuchElementException(String.format("User with id %s does not exist!", userId));

        return new UserInfoDto(
                user.get().getId(),
                user.get().getUsername(),
                user.get().getEmail()
        );
    }
}
