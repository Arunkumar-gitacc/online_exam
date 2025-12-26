package com.jobportel.boot.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class SubmitRequest {
	private Long candidateId;
    private String candidateEmail;

    private List<AnswerDTO> answers; // questionId -> selectedOption

    // Getters and Setters
   
}
