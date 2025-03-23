package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;

import java.util.List;

public interface GetAllQuestionUsecase {
    public List<Question> execute();
}
