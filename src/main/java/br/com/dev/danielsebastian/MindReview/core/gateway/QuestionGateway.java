package br.com.dev.danielsebastian.MindReview.core.gateway;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionGateway {

    Question createQuestion(Question question);
    List<Question> getAllQuestion();
    Optional<Question> getQuestionById(Long id);

}
