package br.com.dev.danielsebastian.MindReview.infra.mappers;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.infra.persistence.QuestionEntity;
import org.springframework.stereotype.Component;

@Component
public class QuestionEntityMapper {
    public Question toDomain(QuestionEntity questionEntity){
        return new Question(
                questionEntity.getId(),
                questionEntity.getText(),
                questionEntity.getResponse(),
                questionEntity.getDifficultyQuestion()
        );
    }

    public QuestionEntity toEntity(Question question){
        return new QuestionEntity(
                question.id(),
                question.text(),
                question.response(),
                question.difficultyQuestion()
        );
    }
}
