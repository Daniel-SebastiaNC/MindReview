package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;
import br.com.dev.danielsebastian.MindReview.infra.exeception.DataNotFoundException;

public class DeleteQuestionByIdUsecaseImpl implements DeleteQuestionByIdUsecase{

    private final QuestionGateway gateway;

    public DeleteQuestionByIdUsecaseImpl(QuestionGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void execute(Long id) {
        Question question = gateway.getQuestionById(id).orElseThrow(() -> new DataNotFoundException("Question with id: " + id + " Not Found"));
        gateway.deleteQuestionById(question);
    }
}
