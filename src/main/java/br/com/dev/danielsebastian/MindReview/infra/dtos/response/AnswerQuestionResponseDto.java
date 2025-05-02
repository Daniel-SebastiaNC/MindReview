package br.com.dev.danielsebastian.MindReview.infra.dtos.response;

import br.com.dev.danielsebastian.MindReview.infra.dtos.QuestionDto;

public record AnswerQuestionResponseDto(
        QuestionDto response,
        boolean result) { }
