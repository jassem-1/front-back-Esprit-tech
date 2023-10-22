package com.full.full.controller;

import com.full.full.models.PieChartData;
import com.full.full.models.Task;
import com.full.full.models.User;
import com.full.full.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@AllArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @PostMapping
    User newUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    @PostMapping("/{userId}/assign-task/{taskId}")
    public ResponseEntity<String> assignTaskToUser(@PathVariable Long userId, @PathVariable Long taskId) {
        userService.assignTaskToUser(taskId, userId);
        return ResponseEntity.ok("Task assigned successfully.");
    }

    @GetMapping("/{userId}/assigned-tasks")
    public ResponseEntity<List<Task>> getAssignedTasksForUser(@PathVariable Long userId) {
        List<Task> tasks = userService.getAssignedTasksForUser(userId);
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/available")
    List<User> getAvailableUsers() {
        return userService.getAvailableUsers();
    }
    @GetMapping("/joined-members")
    public List<User> getJoinedMembers() {
        return userService.getJoinedMembers();
    }

    @PostMapping("/join-chatroom/{username}")
    public void joinChatroom(@PathVariable String username) {
        userService.joinChatroom(username);
    }
    @GetMapping("/{id}/isAssignedToTeam")
    public boolean isUserAssignedToTeam(@PathVariable Long id) {
        return userService.isUserAssignedToTeam(id);
    }

    @GetMapping("/{id}/numberOfAssignedTasks")
    public int getNumberOfAssignedTasks(@PathVariable Long id) {
        return userService.getNumberOfAssignedTasks(id);
    }
    @GetMapping("/countUsersAssignedToTeams")
    public int getNumberOfUsersAssignedToTeams() {
        return userService.getNumberOfUsersAssignedToTeams();
    }
    @GetMapping("/pieChartData")
    public ResponseEntity<PieChartData> getPieChartData() {
        PieChartData data = userService.getPieChartData();
        return ResponseEntity.ok(data);
    }

}

