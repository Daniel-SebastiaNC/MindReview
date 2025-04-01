package br.com.dev.danielsebastian.MindReview.infra.dtos;

import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;

import java.time.LocalDateTime;

public record QuestionDto(Long id,
                          String text,
                          String response,
                          DifficultyQuestion difficultyQuestion,
                          LocalDateTime timeDo,
                          TimeDelay timeDelay,
                          int priority) {
}
