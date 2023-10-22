package com.full.full.service;

import com.full.full.models.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(Project newProject);
    List<Project> getAllProjects();
    Project getProjectById(Long id);
    Project updateProject(Long id, Project updatedProject);

    void deleteProject(Long id);


}