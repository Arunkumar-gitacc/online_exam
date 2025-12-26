package com.jobportel.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportel.boot.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
