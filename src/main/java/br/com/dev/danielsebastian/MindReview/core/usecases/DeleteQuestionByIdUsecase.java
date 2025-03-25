package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;

public interface DeleteQuestionByIdUsecase {
    public void execute(Long id);
}
