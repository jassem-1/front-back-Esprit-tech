package com.full.full.service;

import com.full.full.models.Task;
import com.full.full.models.Team;
import com.full.full.models.TeamTaskCount;
import com.full.full.models.User;
import com.full.full.repository.TeamRepo;
import com.full.full.repository.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeamServiceImpl implements TeamService {
    private final UserRepo userRepo;
    private final TeamRepo teamRepo;

    public TeamServiceImpl(UserRepo userRepo, TeamRepo teamRepo) {
        this.userRepo = userRepo;
        this.teamRepo = teamRepo;
    }

    @Override
    public Team createTeam(Team newTeam) {
        newTeam.setId(null); // This is the line that you need to change
        return teamRepo.save(newTeam);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    @Override
    public Team getTeamById(Long id) {
        return teamRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Team not found with id: " + id));
    }

    @Override
    public Team updateTeam(Long id, Team updatedTeam) {
        Team existingTeam = teamRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Team not found with id: " + id));

        existingTeam.setName(updatedTeam.getName());
        // Add other properties to update as needed

        return teamRepo.save(existingTeam);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepo.deleteById(id);
    }
    @Override
    public void addUserToTeam(Long teamId, Long userId) {
        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new NoSuchElementException("Team not found with id: " + teamId));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));

        user.setTeam(team);
        userRepo.save(user);
    }
    @Override
    public void removeMemberFromTeam(Long teamId, Long memberId) {
        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new NoSuchElementException("Team not found with id: " + teamId));

        User member = userRepo.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + memberId));

        if (member.getTeam() != null && member.getTeam().getId().equals(teamId)) {
            member.setTeam(null);
            userRepo.save(member);
        } else {
            throw new NoSuchElementException("User with id " + memberId + " is not a member of the team with id " + teamId);
        }
    }
    @Override
    public List<TeamTaskCount> getCompletedTaskCounts() {
        List<Team> teams = teamRepo.findAll();
        List<TeamTaskCount> taskCounts = new ArrayList<>();

        for (Team team : teams) {
            int completedTaskCount = countCompletedTasksInTeam(team.getId());

            TeamTaskCount taskCount = new TeamTaskCount();
            taskCount.setTeamName(team.getName());
            taskCount.setCompletedTaskCount(completedTaskCount);

            taskCounts.add(taskCount);
        }

        return taskCounts;
    }
    @Override
    public int countTasksInTeam(Long teamId) {
        Optional<Team> teamOptional = teamRepo.findById(teamId);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            int numberOfTasks= 0;

            for (User user : team.getMembers()) {
                numberOfTasks += user.getAssignedTasks().size();
            }

            return numberOfTasks;
        }
        return 0;
    }
    @Override
    public int countCompletedTasksInTeam(Long teamId) {
        Optional<Team> teamOptional = teamRepo.findById(teamId);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            int completedTaskCount = 0;

            for (User user : team.getMembers()) {
                for (Task task : user.getAssignedTasks()) {
                    if (task.isCompleted()) {
                        completedTaskCount++;
                    }
                }
            }

            return completedTaskCount;
        }
        return 0;
    }
    @Override
    public List<Team> getTeamsWithMembersLessThanThree() {
        return teamRepo.findTeamsWithMembersLessThanThree();
    }

    @Override
    public List<Team> getTeamsWithMembersBetweenFourAndSeven() {
        return teamRepo.findTeamsWithMembersBetweenFourAndSeven();
    }

    @Override
    public List<Team> getTeamsWithMembersMoreThanSeven() {
        return teamRepo.findTeamsWithMembersMoreThanSeven();
    }

}





