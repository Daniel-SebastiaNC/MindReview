package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;

public class CreateQuestionUsecaseImpl implements CreateQuestionUsecase{

    private final QuestionGateway gateway;

    public CreateQuestionUsecaseImpl(QuestionGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Question execute(Question question) {
        return gateway.createQuestion(question);
    }
}
