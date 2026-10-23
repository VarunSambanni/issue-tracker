package com.varunsambanni.issuetracker.service;

import com.varunsambanni.issuetracker.dto.CreateProjectRequest;
import com.varunsambanni.issuetracker.dto.ProjectResponse;
import com.varunsambanni.issuetracker.exception.ProjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProjectServiceTest {
    private ProjectService projectService;

    @BeforeEach
    void setUp(){
        projectService = new ProjectService();
    }

    @Test
    void shouldCreateProject(){
        CreateProjectRequest request = new CreateProjectRequest("Issue Tracker", "Learn Spring Boot");
        ProjectResponse project = projectService.createProject(request) ;
        assertEquals(1L, project.id());
        assertEquals("Issue Tracker", project.name());
        assertEquals("Learn Spring Boot", project.description());
    }

    @Test
    void shouldThrowExceptionWhenProjectDoesNotExist(){
        assertThrows(ProjectNotFoundException.class, () -> projectService.getProjectById(999L));
    }
}
