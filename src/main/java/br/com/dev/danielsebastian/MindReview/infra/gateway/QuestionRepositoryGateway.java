package br.com.dev.danielsebastian.MindReview.infra.gateway;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;
import br.com.dev.danielsebastian.MindReview.infra.mappers.QuestionEntityMapper;
import br.com.dev.danielsebastian.MindReview.infra.persistence.QuestionEntity;
import br.com.dev.danielsebastian.MindReview.infra.persistence.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class QuestionRepositoryGateway implements QuestionGateway {

    private final QuestionRepository questionRepository;
    private final QuestionEntityMapper questionEntityMapper;

    @Override
    public Question createQuestion(Question question) {
        QuestionEntity entity = questionEntityMapper.toEntity(question);
        entity.setPriority(5);
        entity.setTimeDo(LocalDateTime.now());
        entity.setTimeDelay(TimeDelay.NOW);
        return questionEntityMapper.toDomain(
                questionRepository.save(entity)
        );
    }

    @Override
    public List<Question> getAllQuestion() {
        return questionRepository.findAll().stream()
                .map(questionEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id).map(questionEntityMapper::toDomain);
    }

    @Override
    public void deleteQuestionById(Question question) {
        questionRepository.delete(questionEntityMapper.toEntity(question));
    }

    @Override
    public Question updateQuestion(Question questionById, Question question) {
        QuestionEntity entity = questionEntityMapper.toEntity(questionById);

        entity.setText(question.text());
        entity.setResponse(question.response());
        entity.setDifficultyQuestion(question.difficultyQuestion());

        return questionEntityMapper.toDomain(questionRepository.save(entity));
    }

}
