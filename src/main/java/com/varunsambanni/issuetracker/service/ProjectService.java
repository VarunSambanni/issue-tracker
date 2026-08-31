package com.varunsambanni.issuetracker.service;

import com.varunsambanni.issuetracker.dto.ProjectResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    public List<ProjectResponse> getAllProjects() {
        return List.of(
                new ProjectResponse(1L, "Issue Tracker", "Task 1"),
                new ProjectResponse(2L, "Issue Tracker 2", "Task 2")
        );
    }
}
