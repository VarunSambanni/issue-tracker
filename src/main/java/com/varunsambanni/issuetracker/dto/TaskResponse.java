package com.varunsambanni.issuetracker.dto;

import com.varunsambanni.issuetracker.entity.TaskPriority;
import com.varunsambanni.issuetracker.entity.TaskStatus;


import java.time.LocalDate;

public record TaskResponse(
        Long id,
        Long projectId,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDate dueDate
) {
}