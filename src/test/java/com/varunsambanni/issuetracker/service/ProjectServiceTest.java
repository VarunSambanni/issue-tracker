package com.varunsambanni.issuetracker.service;

import com.varunsambanni.issuetracker.dto.CreateProjectRequest;
import com.varunsambanni.issuetracker.dto.ProjectResponse;
import com.varunsambanni.issuetracker.entity.Project;
import com.varunsambanni.issuetracker.exception.ProjectNotFoundException;
import com.varunsambanni.issuetracker.repository.ProjectRepository;
import com.varunsambanni.issuetracker.exception.ProjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void shouldCreateProject(){
        CreateProjectRequest request = new CreateProjectRequest("Issue Tracker", "Learn Spring Boot");
        Project savedProject = mock(Project.class);

        when(savedProject.getId()).thenReturn(1L) ;
        when(savedProject.getName()).thenReturn("Issue Tracker");
        when(savedProject.getDescription()).thenReturn("Learn Spring Boot") ;
        when(projectRepository.save(any(Project.class))).thenReturn(savedProject);
        ProjectResponse response = projectService.createProject(request);

        assertEquals(1L, response.id());
        assertEquals("Issue Tracker", response.name());
        assertEquals("Learn Spring Boot", response.description());
    }

    @Test
    void shouldThrowExceptionWhenProjectDoesNotExist() {
        when(projectRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(ProjectNotFoundException.class, () -> projectService.getProjectById(999L));

    }
}
