package com.hass.bugtracker.dto;

import java.time.LocalDate;

public record UpdateItemDto(Integer id, String title, String description, LocalDate dueDate, boolean isCompleted) {
}
