package br.com.dev.danielsebastian.MindReview.core.gateway;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionGateway {

    Question saveQuestion(Question question);
    List<Question> getAllQuestion();
    Optional<Question> getQuestionById(Long id);
    void deleteQuestionById(Question question);

    void updateQuestionPriority(Question question, int priority, int days);
    List<Question> getAllQuestionNeedReview();

    Question updateQuestionTimeDalyAndTimeDo(Question question);
}
