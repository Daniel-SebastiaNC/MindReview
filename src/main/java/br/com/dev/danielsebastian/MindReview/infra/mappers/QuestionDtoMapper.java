package br.com.dev.danielsebastian.MindReview.infra.mappers;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.infra.dtos.QuestionDto;
import org.springframework.stereotype.Component;

@Component
public class QuestionDtoMapper {
    public Question toDomain(QuestionDto questionDto){
        return new Question(
                questionDto.id(),
                questionDto.text(),
                questionDto.response(),
                questionDto.difficultyQuestion(),
                questionDto.timeDo(),
                questionDto.timeDelay(),
                questionDto.priority()
        );
    }

    public QuestionDto toDto(Question question){
        return new QuestionDto(
                question.id(),
                question.text(),
                question.response(),
                question.difficultyQuestion(),
                question.timeDo(),
                question.timeDelay(),
                question.priority()
        );
    }

}
