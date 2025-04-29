package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;

import java.time.LocalDateTime;
import java.util.List;

public class GetAllQuestionNeedReviewUsecaseImpl implements GetAllQuestionNeedReviewUsecase {

    private final QuestionGateway gateway;

    public GetAllQuestionNeedReviewUsecaseImpl(QuestionGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<Question> execute() {
        List<Question> allQuestion = gateway.getAllQuestion();

        for (Question question : allQuestion) {
            switch (question.timeDelay()){
                case NOW:
                    break;
                case DAY:
                    gateway.updateQuestionPriority(question, 4, 1);
                    break;
                case ONE_WEEK:
                    gateway.updateQuestionPriority(question, 3, 7);
                    break;
                case TWO_WEEK:
                    gateway.updateQuestionPriority(question, 2, 14);
                    break;
                case MONTH:
                    gateway.updateQuestionPriority(question, 1, 30);
                    break;
            }
        }
        return gateway.getAllQuestionNeedReview();
    }
}
