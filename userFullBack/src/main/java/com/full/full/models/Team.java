package com.full.full.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "Team")
@AllArgsConstructor
@NoArgsConstructor
public class Team implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;


    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<User> members;
}
