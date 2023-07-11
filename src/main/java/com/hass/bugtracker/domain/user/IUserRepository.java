package com.hass.bugtracker.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String userEmail);
}
