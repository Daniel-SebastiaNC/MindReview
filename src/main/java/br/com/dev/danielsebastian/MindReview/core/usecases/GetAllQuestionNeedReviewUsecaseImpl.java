package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;

import java.util.List;

public class GetAllQuestionNeedReviewUsecaseImpl implements GetAllQuestionNeedReviewUsecase {

    private final QuestionGateway gateway;

    public GetAllQuestionNeedReviewUsecaseImpl(QuestionGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<Question> execute() {
        gateway.updateQuestionPriority();
        return gateway.getAllQuestionNeedReview();
    }
}
