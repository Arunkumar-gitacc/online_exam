package com.jobportel.boot.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportel.boot.dto.AnswerDTO;
import com.jobportel.boot.dto.SubmitRequest;
import com.jobportel.boot.model.Answer;
import com.jobportel.boot.model.Question;
import com.jobportel.boot.model.Result;
import com.jobportel.boot.repository.AnswerRepository;
import com.jobportel.boot.repository.QuestionRepository;
import com.jobportel.boot.repository.ResultRepository;

@RestController
@RequestMapping("/api/result")
// replaced with frontend container url
@CrossOrigin(origins = "*")
public class ResultController {

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private ResultRepository resultRepo;

    @Autowired
    private AnswerRepository answerRepo;
    
    @PostMapping("/submit")
public Result submitExam(@RequestBody SubmitRequest request) {

    Result existing = resultRepo.findByCandidateEmail(request.getCandidateEmail()).orElse(null);
    if (existing != null) return existing;

    int apt = 0, rea = 0, com = 0;
    
    // 1. Get the TOTAL number of questions existing in the database
    long grandTotalQuestions = questionRepo.count(); 

    for (AnswerDTO dto : request.getAnswers()) {
        Question q = questionRepo.findById(dto.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        boolean correct = q.getCorrectOption().trim().equalsIgnoreCase(dto.getSelectedOption().trim());

        // Save individual answer
        Answer ans = new Answer();
        ans.setCandidateEmail(request.getCandidateEmail());
        ans.setQuestionId(q.getId());
        ans.setSubmittedAnswer(dto.getSelectedOption());
        ans.setCorrectAnswer(q.getCorrectOption());
        ans.setCorrect(correct);
        answerRepo.save(ans);

        if (correct) {
            String section = q.getSection().trim().toUpperCase();
            switch (section) {
                case "APTITUDE": apt++; break;
                case "REASONING": rea++; break;
                case "COMMUNICATION": com++; break;
            }
        }
    }

    int totalCorrect = apt + rea + com;

    // 2. Calculate percentage based on ALL questions in DB, not just attempted ones
    // If there are 50 questions in DB and user only answered 10 (all correct), 
    // percentage will be (10/50)*100 = 20%
    double percentage = (grandTotalQuestions > 0) 
                        ? ((double) totalCorrect / grandTotalQuestions) * 100 
                        : 0.0;

    Result result = new Result();
    result.setCandidateEmail(request.getCandidateEmail());
    result.setAptitudeCorrect(apt);
    result.setReasoningCorrect(rea);
    result.setCommunicationCorrect(com);
    result.setTotalCorrect(totalCorrect);
    result.setPercentage(percentage); 

    return resultRepo.save(result);
}@GetMapping("/email/{email}")
    public Result getResultByEmail(@PathVariable("email") String email) {
        return resultRepo.findByCandidateEmail(email)
            .orElseThrow(() -> new RuntimeException("Result not found"));
    }
}


