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
    public Question saveQuestion(Question question) {
        QuestionEntity entity = questionEntityMapper.toEntity(question);
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
    public void updateQuestionPriority(Question question, int priority, int time) {
        QuestionEntity entity = questionEntityMapper.toEntity(question);
        LocalDateTime now = LocalDateTime.now();
        if (entity.getTimeDo().plusDays(time).isBefore(now)) {
            entity.setPriority(priority);
            entity.setNeedReview(true);
            questionRepository.save(entity);
        }
    }

    @Override
    public List<Question> getAllQuestionNeedReview() {
        return questionRepository.findAllByIsNeedReviewOrderByPriorityAsc(true).stream().map(questionEntityMapper::toDomain).toList();
    }

    @Override
    public Question updateQuestionTimeDalyAndTimeDo(Question question) {
        QuestionEntity entity = questionEntityMapper.toEntity(question);
        entity.setNeedReview(false);
        entity.setTimeDo(LocalDateTime.now());
        switch (entity.getTimeDelay()){
            case NOW:
                entity.setTimeDelay(TimeDelay.DAY);
                break;
            case DAY:
                entity.setTimeDelay(TimeDelay.ONE_WEEK);
                break;
            case ONE_WEEK:
                entity.setTimeDelay(TimeDelay.TWO_WEEK);
                break;
            case TWO_WEEK:
                entity.setTimeDelay(TimeDelay.MONTH);
                break;
            /*
            TODO: after new time delay
            case MONTH:
                break;
                */
        }

        return questionEntityMapper.toDomain(questionRepository.save(entity));
    }

}
