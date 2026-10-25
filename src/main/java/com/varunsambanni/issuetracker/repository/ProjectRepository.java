package com.varunsambanni.issuetracker.repository;

import com.varunsambanni.issuetracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, Long> {

}
