package br.com.dev.danielsebastian.MindReview.infra.dtos;

import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;

public record QuestionDto(Long id,
                          String text,
                          String response,
                          DifficultyQuestion difficultyQuestion) {
}
