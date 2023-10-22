package com.full.full.repository;

import com.full.full.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    User findById(long id);


    List<User> findByTeamIsNull();
    List<User> findByJoined(boolean Joined);
    User findByUsername(String username);
    @Query("SELECT COUNT(u) FROM User u WHERE u.team IS NOT NULL")
    int countUsersAssignedToTeams();
    @Query("SELECT COUNT(u) FROM User u WHERE size(u.assignedTasks) < 2")
    int countUsersWithTasksBelow2();

    // Count users with assigned tasks between 3 and 5
    @Query("SELECT COUNT(u) FROM User u WHERE size(u.assignedTasks) BETWEEN 3 AND 5")
    int countUsersWithTasksBetween3And5();

    // Count users with assigned tasks more than 5
    @Query("SELECT COUNT(u) FROM User u WHERE size(u.assignedTasks) > 5")
    int countUsersWithTasksAbove5();

    // Count users not assigned to teams
    @Query("SELECT COUNT(u) FROM User u WHERE u.team IS NULL")
    int countUsersNotAssignedToTeams();
}
