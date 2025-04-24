package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;

import java.time.LocalDateTime;

public class CreateQuestionUsecaseImpl implements CreateQuestionUsecase{

    private final QuestionGateway gateway;

    public CreateQuestionUsecaseImpl(QuestionGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Question execute(Question question) {
        return gateway.saveQuestion(new Question(
                question.id(),
                question.text(),
                question.response(),
                question.difficultyQuestion(),
                LocalDateTime.now(),
                TimeDelay.NOW,
                5,
                true)
        );
    }
}
