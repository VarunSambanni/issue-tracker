package com.varunsambanni.issuetracker.service;

import com.varunsambanni.issuetracker.dto.CreateTaskRequest;
import com.varunsambanni.issuetracker.dto.TaskResponse;
import com.varunsambanni.issuetracker.dto.UpdateTaskRequest;
import com.varunsambanni.issuetracker.entity.Project;
import com.varunsambanni.issuetracker.entity.Task;
import com.varunsambanni.issuetracker.exception.ProjectNotFoundException;
import com.varunsambanni.issuetracker.exception.TaskNotFoundException;
import com.varunsambanni.issuetracker.repository.ProjectRepository;
import com.varunsambanni.issuetracker.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository,
                       ProjectRepository projectRepository){
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public List<TaskResponse> getTasksForProject(Long projectId){
        findProject(projectId);

        return taskRepository.findAllByProject_Id(projectId)
                .stream().map(this::toResponse)
                .toList();
    }

    public TaskResponse getTask(Long projectId, Long taskId){
        return toResponse(findTask(projectId, taskId)) ;
    }

    @Transactional
    public TaskResponse createTask(Long projectId, CreateTaskRequest request){
        Project project = findProject(projectId) ;

        Task task = new Task(
                request.title(),
                request.description(),
                request.status(),
                request.priority(),
                request.dueDate(),
                project
        );

        Task savedTask = taskRepository.save(task);

        return toResponse(savedTask);
    }

    @Transactional
    public TaskResponse updateTask(Long projectId,
                                   Long taskId,
                                   UpdateTaskRequest request ){
        Task task = findTask(projectId, taskId);
        task.update(
                request.title(),
                request.description(),
                request.status(),
                request.priority(),
                request.dueDate()
        );

        return toResponse(task);
    }

    @Transactional
    public void deleteTask(
            Long projectId,
            Long taskId
    ) {
        Task task = findTask(projectId, taskId);
        taskRepository.delete(task);
    }

    private Project findProject(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(
                        () -> new ProjectNotFoundException(projectId)
                );
    }

    private Task findTask(
            Long projectId,
            Long taskId
    ) {
        return taskRepository
                .findByIdAndProject_Id(taskId, projectId)
                .orElseThrow(
                        () -> new TaskNotFoundException(
                                taskId,
                                projectId
                        )
                );
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getProject().getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate()
        );
    }

}
