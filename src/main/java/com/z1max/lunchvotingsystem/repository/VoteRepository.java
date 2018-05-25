package com.z1max.lunchvotingsystem.repository;

import com.z1max.lunchvotingsystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Optional<Vote> findByUserIdAndDate(int userId, LocalDate date);
}
