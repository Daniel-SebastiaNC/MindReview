package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;

import java.util.List;

public class GetAllQuestionUsecaseImpl implements GetAllQuestionUsecase{

    private final QuestionGateway gateway;

    public GetAllQuestionUsecaseImpl(QuestionGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<Question> execute() {
        return gateway.getAllQuestion();
    }
}
