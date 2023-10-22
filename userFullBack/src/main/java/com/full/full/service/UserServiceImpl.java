package com.full.full.service;

import com.full.full.models.PieChartData;
import com.full.full.models.Task;
import com.full.full.models.User;
import com.full.full.repository.TaskRepo;
import com.full.full.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepository;
    @Autowired
    private TaskRepo taskRepository;

    public UserServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setAccessLevel(updatedUser.getAccessLevel());


        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }

    public boolean verifyAdminCredentials(String email, String password) {
        // Retrieve the stored admin credentials (replace with your actual database logic)
        String storedAdminEmail = "admin@example.com"; // Replace with actual stored email
        String storedAdminPassword = "admin123"; // Replace with actual stored password

        // Compare the provided email and password with stored credentials
        return email.equals(storedAdminEmail) && password.equals(storedAdminPassword);
    }

    @Override
    public void assignTaskToUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (task != null && user != null) {
            task.setAssignee(user);
            taskRepository.save(task);
        }
    }

    @Override
    public List<Task> getAssignedTasksForUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getAssignedTasks();
        }
        return Collections.emptyList();
    }
    @Override
    public List<User> getAvailableUsers() {
        return userRepository.findByTeamIsNull();
    }
    @Override
    public List<User> getJoinedMembers() {
        return userRepository.findByJoined(true);
    }

    @Override
    public void joinChatroom(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setJoined(true);
            userRepository.save(user);
        }
    }
    @Override
    public boolean isUserAssignedToTeam(Long userId) {
        User user = getUserById(userId);
        return user != null && user.isAssignedToTeam();
    }

    @Override
    public int getNumberOfAssignedTasks(Long userId) {
        User user = getUserById(userId);
        return (user != null && user.getAssignedTasks() != null) ? user.getAssignedTasks().size() : 0;
    }
    @Override
    public int getNumberOfUsersAssignedToTeams() {
        return userRepository.countUsersAssignedToTeams();
    }

    @Override
    public int countUsersWithTasksBelow2() {
        return userRepository.countUsersWithTasksBelow2();
    }

    @Override
    public int countUsersWithTasksBetween3And5() {
        return userRepository.countUsersWithTasksBetween3And5();
    }

    @Override
    public int countUsersWithTasksAbove5() {
        return userRepository.countUsersWithTasksAbove5();
    }

    @Override
    public int countUsersAssignedToTeams() {
        return userRepository.countUsersAssignedToTeams();
    }

    @Override
    public int countUsersNotAssignedToTeams() {
        return userRepository.countUsersNotAssignedToTeams();
    }
    public PieChartData getPieChartData() {
        PieChartData data = new PieChartData();
        data.setUsersWithTasksBelow2(userRepository.countUsersWithTasksBelow2());
        data.setUsersWithTasksBetween3And5(userRepository.countUsersWithTasksBetween3And5());
        data.setUsersWithTasksAbove5(userRepository.countUsersWithTasksAbove5());
        data.setUsersAssignedToTeams(userRepository.countUsersAssignedToTeams());
        data.setUsersNotAssignedToTeams(userRepository.countUsersNotAssignedToTeams());
        return data;
    }

}
