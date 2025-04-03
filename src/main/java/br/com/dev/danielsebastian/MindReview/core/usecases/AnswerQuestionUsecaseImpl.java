package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.AnswerQuestion;
import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;
import br.com.dev.danielsebastian.MindReview.infra.exeception.DataNotFoundException;

public class AnswerQuestionUsecaseImpl implements AnswerQuestionUsecase {

    private final QuestionGateway gateway;

    public AnswerQuestionUsecaseImpl(QuestionGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Question execute(Long id, AnswerQuestion answerQuestion) {
        Question questionById = gateway.getQuestionById(id).orElseThrow(() -> new DataNotFoundException("Question with id: " + id + " Not Found"));
        if (questionById.response().equalsIgnoreCase(answerQuestion.response())) {
            return gateway.updateQuestionTimeDalyAndTimeDo(questionById);
        }
        return questionById;
    }
}
