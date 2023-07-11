package com.hass.bugtracker.presentation.user;

import com.hass.bugtracker.presentation.user.dto.NewUserDto;
import com.hass.bugtracker.presentation.user.dto.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService _userService;

    @Autowired
    public UserController(UserService userService) { _userService = userService; }

    @GetMapping
    public List<UserInfoDto> getUsers() { return _userService.getUsers(); }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Integer userId) {
        try {
            UserInfoDto user = _userService.findById(userId);

            return new ResponseEntity<UserInfoDto>(user, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        try {
            _userService.deleteUser(userId);

            return new ResponseEntity<>(String.format("The user %s was deleted successfully!", userId), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {

            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody NewUserDto newUserDto) {
        try {
            _userService.createUser(newUserDto);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(String.format("User %s was created successfully!", newUserDto.username()), HttpStatus.OK);
    }

}
