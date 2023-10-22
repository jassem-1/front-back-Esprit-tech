package com.full.full.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamTaskCount {
    private String teamName;
    private int completedTaskCount;

}
