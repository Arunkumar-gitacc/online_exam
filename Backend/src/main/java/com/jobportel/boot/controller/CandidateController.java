package com.jobportel.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportel.boot.model.Candidate;
import com.jobportel.boot.repository.CandidateRepository;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin(origins = "http://localhost:3000")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/register")
    public Candidate register(@RequestBody Candidate candidate) {

        // 1. Validate Email (Case-insensitive check is better)
        if (candidateRepository.findByEmail(candidate.getEmail()).isPresent()) {
            throw new RuntimeException("Email " + candidate.getEmail() + " is already registered.");
        }

        // 2. Validate Phone
        if (candidateRepository.findByPhone(candidate.getPhone()).isPresent()) {
            throw new RuntimeException("Phone number is already registered.");
        }

        // The 'gender' and 'branch' sent from React will be 
        // automatically saved if they exist in your Candidate model.
        return candidateRepository.save(candidate);
    }

    @GetMapping("/{id}")
    public Candidate getCandidate(@PathVariable("id") Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + id));
    }
}