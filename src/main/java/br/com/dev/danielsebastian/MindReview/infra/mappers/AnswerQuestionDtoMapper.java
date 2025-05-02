package br.com.dev.danielsebastian.MindReview.infra.mappers;

import br.com.dev.danielsebastian.MindReview.core.domians.AnswerQuestion;
import br.com.dev.danielsebastian.MindReview.infra.dtos.request.AnswerQuestionRequestDto;
import org.springframework.stereotype.Component;

@Component
public class AnswerQuestionDtoMapper {
    public AnswerQuestion toDomain(AnswerQuestionRequestDto answerQuestionDto){
        return new AnswerQuestion(
                answerQuestionDto.response()
        );
    }

    public AnswerQuestionRequestDto toDto(AnswerQuestion answerQuestion){
        return new AnswerQuestionRequestDto(
                answerQuestion.response()
        );
    }
}
