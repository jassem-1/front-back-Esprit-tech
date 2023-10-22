package com.full.full.repository;

import com.full.full.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepo extends JpaRepository<Task,Long> {
    Task findById(long id);
    int countByCompletedTrue();
    int countByAssigneeIsNull();
    @Query("SELECT COUNT(t) FROM Task t WHERE t.completed = false")
    int countUncompletedTasks();
}
