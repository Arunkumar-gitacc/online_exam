package com.jobportel.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jobportel.boot.model.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    Optional<Result> findByCandidateEmail(String candidateEmail);
}

