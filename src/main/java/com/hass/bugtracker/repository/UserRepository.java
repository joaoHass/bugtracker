package com.hass.bugtracker.repository;

import com.hass.bugtracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
