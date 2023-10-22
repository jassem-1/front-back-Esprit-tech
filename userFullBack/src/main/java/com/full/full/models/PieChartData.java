package com.full.full.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PieChartData {
    private int usersWithTasksBelow2;
    private int usersWithTasksBetween3And5;
    private int usersWithTasksAbove5;
    private int usersAssignedToTeams;
    private int usersNotAssignedToTeams;
}
