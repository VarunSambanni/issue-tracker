package com.varunsambanni.issuetracker.service;

import com.varunsambanni.issuetracker.dto.CreateProjectRequest;
import com.varunsambanni.issuetracker.dto.ProjectResponse;
import com.varunsambanni.issuetracker.dto.UpdateProjectRequest;

import com.varunsambanni.issuetracker.exception.ProjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.varunsambanni.issuetracker.entity.Project;
import com.varunsambanni.issuetracker.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// @Transactional(readOnly = true) makes reading the default for this service. Write methods override it with @Transactional.

@Service
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional
    public ProjectResponse createProject(CreateProjectRequest request){
        Project project = new Project(request.name(), request.description());
        Project savedProject = projectRepository.save(project);
        return toResponse(savedProject);
    }

    public ProjectResponse getProjectById(Long id){
        return toResponse(findProject(id));
    }

    @Transactional
    public ProjectResponse updateProject(Long id, UpdateProjectRequest request){
        Project project = findProject(id) ;
        project.update(request.name(), request.description()) ;
        return toResponse(project);
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = findProject(id) ;
        projectRepository.delete(project);
    }

    private Project findProject(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id)) ;
    }

    private ProjectResponse toResponse(Project project){
        return new ProjectResponse(project.getId(), project.getName(), project.getDescription());
    }

}
