package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.AnswerQuestion;
import br.com.dev.danielsebastian.MindReview.core.domians.Question;

public interface AnswerQuestionUsecase {
    Question execute(Long id, AnswerQuestion answerQuestion);
}


