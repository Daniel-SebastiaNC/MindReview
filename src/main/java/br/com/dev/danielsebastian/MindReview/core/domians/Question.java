package br.com.dev.danielsebastian.MindReview.core.domians;

import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;

public record Question(Long id,
                       String text,
                       String response,
                       DifficultyQuestion difficultyQuestion) {
}
