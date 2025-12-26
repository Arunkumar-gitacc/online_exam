package com.jobportel.boot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportel.boot.model.Candidate;
import com.jobportel.boot.model.Question;
import com.jobportel.boot.repository.CandidateRepository;
import com.jobportel.boot.repository.QuestionRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://frontend:8081")
public class ExamController {

    @Autowired
    private CandidateRepository candidateRepo;

    @Autowired
    private QuestionRepository questionRepo;


    // QUESTIONS
    @GetMapping("/questions/{section}")
    public List<Question> getQuestions(@PathVariable("section") String section) {
        return questionRepo.findBySection(section);
    }

}

