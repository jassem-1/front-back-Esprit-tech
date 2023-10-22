package com.full.full.repository;

import com.full.full.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Long> {
    Project findById(long id);
}