package com.full.full.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String name ;
    private String email ;
    private String phone ;
    private String accessLevel ;
    private boolean joined;



    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "team_id")
    private Team team;
    @Transient
    private Long teamId;
    public void setTeam(Team team) {
        this.team = team;
        this.teamId = team != null ? team.getId() : null;
    }
    public Long getTeamId() {
        return team != null ? team.getId() : null;
    }

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    private List<Task> assignedTasks;
    public boolean isAssignedToTeam() {
        return team != null;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notes> notes;
}
