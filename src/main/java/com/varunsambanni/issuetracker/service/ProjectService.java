package com.varunsambanni.issuetracker.service;

import com.varunsambanni.issuetracker.dto.CreateProjectRequest;
import com.varunsambanni.issuetracker.dto.ProjectResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private final List<ProjectResponse> projects = new ArrayList<>();
    private long nextId = 1;


    public List<ProjectResponse> getAllProjects() {
        return List.copyOf(projects) ;
    }

    public ProjectResponse createProject(CreateProjectRequest request){
        ProjectResponse project = new ProjectResponse(nextId++, request.name(), request.description());

        projects.add(project) ;
        return project;
    }
}
