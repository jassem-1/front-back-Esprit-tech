package com.full.full.service;

import com.full.full.models.Team;
import com.full.full.models.TeamTaskCount;
import com.full.full.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface TeamService {
    Team createTeam(Team newTeam);
    List<Team> getAllTeams();
    Team getTeamById(Long id);
    Team updateTeam(Long id, Team updatedTeam);
    void deleteTeam(Long id);
    void addUserToTeam(Long teamId, Long userId);
    void removeMemberFromTeam(Long teamId, Long memberId);
    int countCompletedTasksInTeam(Long teamId);
    int countTasksInTeam(Long teamId);
    List<TeamTaskCount> getCompletedTaskCounts();
    List<Team> getTeamsWithMembersLessThanThree();
    List<Team> getTeamsWithMembersBetweenFourAndSeven();
    List<Team> getTeamsWithMembersMoreThanSeven();
}



