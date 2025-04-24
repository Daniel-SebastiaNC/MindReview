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
    public void updateQuestionPriority() {
        List<QuestionEntity> all = questionRepository.findAll();
        for (QuestionEntity questionEntity : all) {
            LocalDateTime now = LocalDateTime.now();
            switch (questionEntity.getTimeDelay()){
                case NOW:
                    questionEntity.setPriority(5);
                    questionEntity.setNeedReview(true);
                    break;
                case DAY:
                    if (questionEntity.getTimeDo().plusDays(1).isBefore(now)){
                        questionEntity.setPriority(4);
                        questionEntity.setNeedReview(true);
                    }
                    break;
                case ONE_WEEK:
                    if (questionEntity.getTimeDo().plusWeeks(1).isBefore(now)){
                        questionEntity.setPriority(3);
                        questionEntity.setNeedReview(true);
                    }
                    break;
                case TWO_WEEK:
                    if (questionEntity.getTimeDo().plusWeeks(2).isBefore(now)){
                        questionEntity.setPriority(2);
                        questionEntity.setNeedReview(true);
                    }
                    break;
                case MONTH:
                    if (questionEntity.getTimeDo().plusMonths(1).isBefore(now)){
                        questionEntity.setPriority(1);
                        questionEntity.setNeedReview(true);
                    }
                    break;
                default:
                    questionEntity.setPriority(6);
                    break;
            }

            questionRepository.save(questionEntity);
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
