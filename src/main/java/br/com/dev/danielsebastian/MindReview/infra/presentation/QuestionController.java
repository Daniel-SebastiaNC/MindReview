package br.com.dev.danielsebastian.MindReview.infra.presentation;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.usecases.CreateQuestionUsecase;
import br.com.dev.danielsebastian.MindReview.core.usecases.GetAllQuestionUsecase;
import br.com.dev.danielsebastian.MindReview.core.usecases.GetQuestionByIdUsecase;
import br.com.dev.danielsebastian.MindReview.infra.dtos.QuestionDto;
import br.com.dev.danielsebastian.MindReview.infra.mappers.QuestionDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/question")
public class QuestionController {

    private final CreateQuestionUsecase createQuestionUsecase;
    private final GetAllQuestionUsecase getAllQuestionUsecase;
    private final GetQuestionByIdUsecase getQuestionByIdUsecase;
    private final QuestionDtoMapper questionDtoMapper;

    @PostMapping("/add")
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto) {
        Question question = questionDtoMapper.toDomain(questionDto);
        Question saveQuestion = createQuestionUsecase.execute(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(questionDtoMapper.toDto(saveQuestion));
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuestionDto>> getAllQuestion(){
        return ResponseEntity.ok(
                getAllQuestionUsecase.execute().stream()
                        .map(questionDtoMapper::toDto)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable Long id){
        return ResponseEntity.ok(
                questionDtoMapper.toDto(
                        getQuestionByIdUsecase.execute(id)
                )
        );
    }
}
