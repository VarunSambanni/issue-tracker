package com.varunsambanni.issuetracker.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long taskId, Long projectId){
        super( "Task with id " + taskId + " was not found in project " + projectId);
    }
}
