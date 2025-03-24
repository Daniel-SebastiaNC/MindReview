package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;
import br.com.dev.danielsebastian.MindReview.infra.exeception.DataNotFoundException;

public class GetQuestionByIdUsecaseImpl implements GetQuestionByIdUsecase {

    private final QuestionGateway gateway;

    public GetQuestionByIdUsecaseImpl(QuestionGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Question execute(Long id) {
        return gateway.getQuestionById(id).orElseThrow(() -> new DataNotFoundException("Question with id: " + id + " Not Found"));
    }
}
