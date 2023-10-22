package com.full.full.service;

import com.full.full.models.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task newTask);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task updateTask(Long id, Task updatedTask);
    void deleteTask(Long id);
    void addTaskToProject(Long projectId, Long taskId);
    void removeTaskFromProject(Long projectId, Long taskId);
    void markTaskAsCompleted(Long taskId);
    int getNumberOfCompletedTasks();
    int getNumberOfUnassignedTasks();
    int getNumberOfUncompletedTasks();


}