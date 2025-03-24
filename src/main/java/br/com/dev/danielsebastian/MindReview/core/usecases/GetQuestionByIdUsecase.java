package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;

public interface GetQuestionByIdUsecase {
    public Question execute(Long id);
}
