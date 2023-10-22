package com.full.full.controller;

import com.full.full.models.Team;
import com.full.full.models.TeamTaskCount;
import com.full.full.models.User;
import com.full.full.service.TaskService;
import com.full.full.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000/")
@AllArgsConstructor
@RequestMapping("teams")
public class TeamController {
    private final TeamService teamService;


    @PostMapping
    Team newTeam(@RequestBody Team newTeam) {
        return teamService.createTeam(newTeam);
    }

    @GetMapping
    List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    Team getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @PutMapping("/{id}")
    Team updateTeam(@PathVariable Long id, @RequestBody Team updatedTeam) {
        return teamService.updateTeam(id, updatedTeam);
    }

    @DeleteMapping("/{id}")
    void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }
    @PostMapping("/{teamId}/addUser/{userId}")
    Team addUserToTeam(@PathVariable Long teamId, @PathVariable Long userId) {
        teamService.addUserToTeam(teamId, userId);
        return teamService.getTeamById(teamId);
    }
    @DeleteMapping("/{teamId}/removeMember/{memberId}")
    void removeMemberFromTeam(@PathVariable Long teamId, @PathVariable Long memberId) {
        teamService.removeMemberFromTeam(teamId, memberId);
    }
    @GetMapping("/completed-task-count")
    public ResponseEntity<List<TeamTaskCount>> getCompletedTaskCounts() {
        List<TeamTaskCount> taskCounts = teamService.getCompletedTaskCounts();
        return ResponseEntity.ok(taskCounts);
    }
    @GetMapping("/members-less-than-three")
    List<Team> getTeamsWithMembersLessThanThree() {
        return teamService.getTeamsWithMembersLessThanThree();
    }

    @GetMapping("/members-between-four-and-seven")
    List<Team> getTeamsWithMembersBetweenFourAndSeven() {
        return teamService.getTeamsWithMembersBetweenFourAndSeven();
    }

    @GetMapping("/members-more-than-seven")
    List<Team> getTeamsWithMembersMoreThanSeven() {
        return teamService.getTeamsWithMembersMoreThanSeven();
    }


}
