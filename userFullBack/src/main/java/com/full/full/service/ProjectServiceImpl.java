package com.full.full.service;

import com.full.full.models.Project;
import com.full.full.repository.ProjectRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProjectServiceImpl implements  ProjectService{
    private ProjectRepo projectRepository;

    public ProjectServiceImpl(ProjectRepo projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createProject(Project newProject) {
        return projectRepository.save(newProject);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Project not found with id: " + id));
    }

    @Override
    public Project updateProject(Long id, Project updatedProject) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Project  not found with id: " + id));

        existingProject.setName(updatedProject.getName());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setEndDate(updatedProject.getEndDate());
        existingProject.setStartDate(updatedProject.getStartDate());
        existingProject.updateProgress();
        existingProject.updatePerformance();

        return projectRepository.save(existingProject);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);

    }

}