package com.full.full.controller;

import com.full.full.models.Project;
import com.full.full.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@AllArgsConstructor
@RequestMapping("projects")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    Project newProject(@RequestBody Project newProject) {
        return projectService.createProject(newProject);
    }

    @GetMapping
    List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PutMapping("/{id}")
    Project updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        return projectService.updateProject(id, updatedProject);
    }

    @DeleteMapping("/{id}")
    void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}