package com.hass.bugtracker.dto;

import java.time.LocalDate;

public record ItemDto(String title, String description, LocalDate dueDate, boolean isCompleted) {
}
