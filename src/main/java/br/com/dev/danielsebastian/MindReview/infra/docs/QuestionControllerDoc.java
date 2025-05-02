package br.com.dev.danielsebastian.MindReview.infra.docs;

import br.com.dev.danielsebastian.MindReview.infra.dtos.request.AnswerQuestionRequestDto;
import br.com.dev.danielsebastian.MindReview.infra.dtos.response.AnswerQuestionResponseDto;
import br.com.dev.danielsebastian.MindReview.infra.dtos.QuestionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Tag(name = "Question", description = "Resource responsible for managing Question.")
public interface QuestionControllerDoc {

    @Operation(
            summary = "Create Question",
            description = "Method responsible for registering the Question in the system."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Returns the registered Question.",
            content = @Content(schema = @Schema(implementation = QuestionDto.class))
    )
    ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto);

    @Operation(
            summary = "List All Question",
            description = "Method responsible for list all Question from system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Returns all Questions.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = QuestionDto.class)))
    )
    ResponseEntity<List<QuestionDto>> getAllQuestion();

    @Operation(
            summary = "Get Question by Id",
            description = "Method responsible for find Question by Id from system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Returns Question by found by Id.",
            content = @Content(schema = @Schema(implementation = QuestionDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Returns errors: Question not Found.",
            content = @Content(schema = @Schema(implementation = Map.class))
    )
    ResponseEntity<QuestionDto> getQuestionById(@PathVariable Long id);

    @Operation(
            summary = "Delete Question by Id",
            description = "Method responsible for delete Question by Id from system."
    )
    @ApiResponse(
            responseCode = "204",
            description = "Deleted Question by found by Id.",
            content = @Content(schema = @Schema(implementation = QuestionDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Returns errors: Question not Found.",
            content = @Content(schema = @Schema(implementation = Map.class))
    )
    ResponseEntity<Void> deleteQuestionById(@PathVariable Long id);

    @Operation(
            summary = "Update Question by Id",
            description = "Method responsible for update Question by Id from system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Updated Question by found by Id.",
            content = @Content(schema = @Schema(implementation = QuestionDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Returns errors: Question not Found.",
            content = @Content(schema = @Schema(implementation = Map.class))
    )
    ResponseEntity<QuestionDto> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDto);

    @Operation(
            summary = "List All Question that need review",
            description = "Method responsible for list all Question that need review from system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Returns all Questions that need review.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = QuestionDto.class)))
    )
    ResponseEntity<List<QuestionDto>> getAllQuestionNeedReview();

    @Operation(
            summary = "Answer Question",
            description = "Method responsible for answer Question from system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Returns Question if is correct or incorrect.",
            content = @Content(
                    schema = @Schema(implementation = AnswerQuestionResponseDto.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Returns errors: Question not Found.",
            content = @Content(schema = @Schema(implementation = Map.class))
    )
    ResponseEntity<AnswerQuestionResponseDto> answerQuestion(@PathVariable Long id, @RequestBody AnswerQuestionRequestDto answerQuestionDto);
}
