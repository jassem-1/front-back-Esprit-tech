package com.full.full.service;

import com.full.full.exceptions.TaskNotFoundException;
import com.full.full.models.Project;
import com.full.full.models.Task;
import com.full.full.models.Team;
import com.full.full.repository.ProjectRepo;
import com.full.full.repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final ProjectRepo projectRepo;

    public TaskServiceImpl(TaskRepo taskRepo, ProjectRepo projectRepo) {
        this.taskRepo = taskRepo;
        this.projectRepo = projectRepo;
    }
    @Override
    public Task createTask(Task newTask) {
        newTask.setId(null); // This is the line that you need to change
        return taskRepo.save(newTask);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task not found with id: " + id));
    }

    @Override
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Team not found with id: " + id));

        existingTask.setName(updatedTask.getName());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setDeadline(updatedTask.getDeadline());
        existingTask.setCompleted((updatedTask.isCompleted()));
        existingTask.setProject(updatedTask.getProject());

        existingTask.markAsCompleted();

        return taskRepo.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
    @Override
    public void addTaskToProject(Long projectId, Long taskId) {
        Project project= projectRepo.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project not found with id: " + projectId));

        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with id: " + taskId));

        task.setProject(project);
        taskRepo.save(task);
    }
    @Override
    public void removeTaskFromProject(Long projectId, Long taskId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project not found with id: " + projectId));

        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with id: " + taskId));

        if (task.getProject() != null && task.getProject().getId().equals(projectId)) {
            task.setProject(null);
            taskRepo.save(task);
        } else {
            throw new NoSuchElementException("User with id " + taskId + " is not a member of the team with id " + projectId);
        }
    }

    @Override
    public void markTaskAsCompleted(Long taskId) {
        Optional<Task> taskOptional = taskRepo.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.markAsCompleted();
            taskRepo.save(task); // Save the updated task
        } else {
            throw new TaskNotFoundException("Task not found with ID: " + taskId);
        }
    }
    @Override
    public int getNumberOfCompletedTasks() {
        return taskRepo.countByCompletedTrue();
    }
    @Override
    public int getNumberOfUnassignedTasks() {
        return taskRepo.countByAssigneeIsNull();
    }
    @Override
    public int getNumberOfUncompletedTasks() {
        return taskRepo.countUncompletedTasks();
    }


}