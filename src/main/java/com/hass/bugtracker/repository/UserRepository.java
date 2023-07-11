package com.hass.bugtracker.repository;

import com.hass.bugtracker.domain.user.IUserRepository;
import com.hass.bugtracker.domain.user.User;

import java.util.Optional;

public interface UserRepository extends IUserRepository {

    Optional<User> findUserByEmail(String userEmail);
}
