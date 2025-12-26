package com.jobportel.boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportel.boot.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByEmail(String email);
    Optional<Candidate> findByPhone(String phone);
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
