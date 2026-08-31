package com.varunsambanni.issuetracker.controller;

import com.varunsambanni.issuetracker.service.ApplicationStatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.varunsambanni.issuetracker.dto.ApplicationStatusResponse;


@RestController
@RequestMapping("/api")
public class HelloController{

    private final ApplicationStatusService statusService;

    public HelloController(ApplicationStatusService statusService){
        this.statusService = statusService;
    }

    @GetMapping("/hello")
    public ApplicationStatusResponse hello(){
        return new ApplicationStatusResponse("issue-tracker", statusService.getStatusMessage());
    }
}