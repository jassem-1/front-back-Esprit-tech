package com.full.full.repository;

import com.full.full.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {
    Team findById(long id);
    @Query("SELECT t FROM Team t WHERE SIZE(t.members) < 3")
    List<Team> findTeamsWithMembersLessThanThree();

    @Query("SELECT t FROM Team t WHERE SIZE(t.members) >7")
    List<Team> findTeamsWithMembersMoreThanSeven();
    @Query("SELECT t FROM Team t WHERE SIZE(t.members) BETWEEN 3 AND 7")
    List<Team> findTeamsWithMembersBetweenFourAndSeven();

}
