package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;
import br.com.dev.danielsebastian.MindReview.infra.exeception.DataNotFoundException;

public class UpdateQuestionUsecaseImpl implements UpdateQuestionUsecase{

    private final QuestionGateway gateway;

    public UpdateQuestionUsecaseImpl(QuestionGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Question execute(Long id, Question question) {
        Question questionById = gateway.getQuestionById(id).orElseThrow(() -> new DataNotFoundException("Question with id: " + id + " Not Found"));

        return gateway.saveQuestion(new Question(
                questionById.id(),
                question.text().isEmpty() ? questionById.text() : question.text(),
                question.response().isEmpty() ? questionById.response() : question.response(),
                String.valueOf(question.difficultyQuestion()).isEmpty() || question.difficultyQuestion() == null ? questionById.difficultyQuestion() : question.difficultyQuestion(),
                questionById.timeDo(),
                questionById.timeDelay(),
                questionById.priority(),
                questionById.isNeedReview())
        );
    }
}
