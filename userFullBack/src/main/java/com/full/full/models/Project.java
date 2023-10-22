package com.full.full.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "Project")
@AllArgsConstructor
@NoArgsConstructor

public class Project implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)

    private List<Task> tasks;
    private int progressPercentage;
    private int performanceScore;


    public void updateProgress() {
        int totalTasks = tasks.size();
        int completedTasks = 0;

        for (Task task : tasks) {
            if (task.isCompleted()) {
                completedTasks++;
            }
        }

        progressPercentage = (int) ((completedTasks / (double) totalTasks) * 100);
    }
    public double calculateCompletionRate() {
        int totalTasks = tasks.size();
        int completedTasks = 0;

        for (Task task : tasks) {
            if (task.isCompleted()) {
                completedTasks++;
            }
        }

        if (totalTasks == 0) {
            return 0.0; // To avoid division by zero error
        }

        return (double) completedTasks / totalTasks;
    }
    public double calculateDeadlineAdherence() {
        if (tasks.isEmpty()) {
            return 0.0; // No tasks to evaluate
        }

        int totalTasks = tasks.size();
        int adheredDeadlines = 0;

        for (Task task : tasks) {
            if (task.isCompleted() || LocalDate.now().isBefore(task.getDeadline())) {
                adheredDeadlines++;
            }
        }

        return (double) adheredDeadlines / totalTasks;
    }
    public int calculatePerformanceScore() {
        double completionRate = calculateCompletionRate(); // Calculate the completion rate
        double deadlineAdherence = calculateDeadlineAdherence(); // Calculate the adherence to deadlines

        // Assign weights to each factor
        double completionRateWeight = 0.6;
        double deadlineAdherenceWeight = 0.4;

        // Calculate the weighted scores
        double completionRateScore = completionRate * completionRateWeight;
        double deadlineAdherenceScore = deadlineAdherence * deadlineAdherenceWeight;

        // Calculate the performance score by summing up the weighted scores
        double performanceScore = completionRateScore + deadlineAdherenceScore;

        // Normalize the performance score if necessary
        // You can apply any scaling or normalization logic here based on your requirements

        return (int) performanceScore; // Return the performance score as an integer
    }


    public void updatePerformance() {
        // Logic to calculate performance score based on various factors
        // You can consider factors such as task quality, adherence to deadlines, etc.
        performanceScore = calculatePerformanceScore();
    }
}