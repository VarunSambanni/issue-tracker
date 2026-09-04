package com.varunsambanni.issuetracker.service;

import com.varunsambanni.issuetracker.dto.CreateProjectRequest;
import com.varunsambanni.issuetracker.dto.ProjectResponse;
import com.varunsambanni.issuetracker.dto.UpdateProjectRequest;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<ProjectResponse> getProjectById(Long id){
        return projects.stream().filter(project -> project.id().equals(id)).findFirst();
    }

    public Optional<ProjectResponse> updateProject(Long id, UpdateProjectRequest request){
        for (int index = 0; index < projects.size(); index++){
            ProjectResponse existingProject = projects.get(index) ;
            if (existingProject.id().equals(id)){
                ProjectResponse updatedProject = new ProjectResponse(id, request.name(), request.description());
                projects.set(index, updatedProject);
                return Optional.of(updatedProject);
            }
        }
        return Optional.empty();
    }

    public boolean deleteProject(Long id) {
        return projects.removeIf(project -> project.id().equals(id));

    }
}
