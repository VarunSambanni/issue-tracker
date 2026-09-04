package com.varunsambanni.issuetracker.controller;

import com.varunsambanni.issuetracker.dto.ProjectResponse;
import com.varunsambanni.issuetracker.dto.CreateProjectRequest;
import com.varunsambanni.issuetracker.dto.UpdateProjectRequest;

import com.varunsambanni.issuetracker.service.ProjectService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectResponse> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse createProject(
            @RequestBody CreateProjectRequest request
    ) {
        return projectService.createProject(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(
            @PathVariable Long id
    ) {
        Optional<ProjectResponse> project = projectService.getProjectById(id);

        if (project.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(project.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long id, @RequestBody UpdateProjectRequest request
    ){
        Optional<ProjectResponse> updateProject = projectService.updateProject(id, request) ;
        if (updateProject.isEmpty()) {
            return ResponseEntity.notFound().build() ;
        }
        return ResponseEntity.ok(updateProject.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id){
        boolean deleted = projectService.deleteProject(id);

        if (!deleted){
            return ResponseEntity.notFound().build() ;
        }
        return ResponseEntity.noContent().build();
    }
}
