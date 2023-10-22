package com.full.full.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.full.full.models.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Task")
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private LocalDate deadline;
    private boolean completed;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @JsonIgnoreProperties("tasks")
    private Project project;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee_id")
    @JsonIgnoreProperties("assignedTasks")
    private User assignee;

    public void markAsCompleted() {
        completed = true;
        if (project != null) {
            project.updateProgress(); // Update the project's progress when a task is completed
        }
    }


}