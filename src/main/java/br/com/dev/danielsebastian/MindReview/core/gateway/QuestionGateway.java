package br.com.dev.danielsebastian.MindReview.core.gateway;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;

public interface QuestionGateway {
    Question createQuestion(Question question);
}
