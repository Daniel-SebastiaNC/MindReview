package br.com.dev.danielsebastian.MindReview.infra.presentation;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.usecases.*;
import br.com.dev.danielsebastian.MindReview.infra.dtos.request.AnswerQuestionRequestDto;
import br.com.dev.danielsebastian.MindReview.infra.dtos.response.AnswerQuestionResponseDto;
import br.com.dev.danielsebastian.MindReview.infra.dtos.QuestionDto;
import br.com.dev.danielsebastian.MindReview.infra.mappers.AnswerQuestionDtoMapper;
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
    private final DeleteQuestionByIdUsecase deleteQuestionByIdUsecase;
    private final UpdateQuestionUsecase updateQuestionUsecase;
    private final GetAllQuestionNeedReviewUsecase getAllQuestionNeedReviewUsecase;
    private final AnswerQuestionUsecase answerQuestionUsecase;
    private final QuestionDtoMapper questionDtoMapper;
    private final AnswerQuestionDtoMapper answerQuestionDtoMapper;


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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable Long id){
        deleteQuestionByIdUsecase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDto){
        Question questionUpdate = updateQuestionUsecase.execute(id, questionDtoMapper.toDomain(questionDto));
        return ResponseEntity.ok(questionDtoMapper.toDto(questionUpdate));
    }

    @GetMapping("/all-review")
    public ResponseEntity<List<QuestionDto>> getAllQuestionNeedReview(){
        return ResponseEntity.ok(
                getAllQuestionNeedReviewUsecase.execute().stream().map(questionDtoMapper::toDto).toList()
        );
    }

    @PostMapping("/answer/{id}")
    public ResponseEntity<AnswerQuestionResponseDto> answerQuestion(@PathVariable Long id, @RequestBody AnswerQuestionRequestDto answerQuestionDto){
        QuestionDto question = questionDtoMapper.toDto(
                answerQuestionUsecase.execute(id, answerQuestionDtoMapper.toDomain(answerQuestionDto))
        );

        return ResponseEntity.ok(new AnswerQuestionResponseDto(question, !question.isNeedReview()));
    }
}
