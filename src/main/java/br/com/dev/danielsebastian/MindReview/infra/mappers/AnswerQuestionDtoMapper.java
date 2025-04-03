package br.com.dev.danielsebastian.MindReview.infra.mappers;

import br.com.dev.danielsebastian.MindReview.core.domians.AnswerQuestion;
import br.com.dev.danielsebastian.MindReview.infra.dtos.AnswerQuestionDto;
import org.springframework.stereotype.Component;

@Component
public class AnswerQuestionDtoMapper {
    public AnswerQuestion toDomain(AnswerQuestionDto answerQuestionDto){
        return new AnswerQuestion(
                answerQuestionDto.response()
        );
    }

    public AnswerQuestionDto toDto(AnswerQuestion answerQuestion){
        return new AnswerQuestionDto(
                answerQuestion.response()
        );
    }
}
