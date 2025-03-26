package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;

public interface UpdateQuestionUsecase {
    public Question execute(Long id, Question question);
}
