package com.hass.bugtracker.presentation.item.dto;

import java.time.LocalDate;

public record UpdateItemDto(Integer id, String title, String description, LocalDate dueDate, boolean isCompleted) {
}
