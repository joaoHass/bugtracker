package com.hass.bugtracker.presentation.item.dto;

import java.time.LocalDate;

public record ItemDto(String title, String description, LocalDate dueDate, Integer createdByUserId, boolean isCompleted) {
}
