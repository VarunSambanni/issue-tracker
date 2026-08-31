package com.varunsambanni.issuetracker.service;
import org.springframework.stereotype.Service;

@Service
public class ApplicationStatusService {

    public String getStatusMessage() {
        return "Issue Tracker is running";
    }
}
