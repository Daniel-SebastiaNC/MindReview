package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllQuestionUsecaseTest {

    @Mock
    private QuestionGateway gateway;

    private GetAllQuestionUsecase getAllQuestionUsecase;

    @BeforeEach
    void setUp() {
        getAllQuestionUsecase = new GetAllQuestionUsecaseImpl(gateway);
    }

    @Test
    void getAllQuestionDataFounded() {
        // Arrange
        List<Question> expected = new ArrayList<>(Arrays.asList(
                new Question(
                        1L,
                        "Test text",
                        "Test response",
                        DifficultyQuestion.EASY,
                        LocalDateTime.now(),
                        TimeDelay.NOW,
                        5,
                        true
                ),
                new Question(
                        2L,
                        "Test text",
                        "Test response",
                        DifficultyQuestion.EASY,
                        LocalDateTime.now(),
                        TimeDelay.NOW,
                        5,
                        true
                ),
                new Question(
                        3L,
                        "Test text",
                        "Test response",
                        DifficultyQuestion.EASY,
                        LocalDateTime.now(),
                        TimeDelay.NOW,
                        5,
                        true
                )
        )
        );

        when(gateway.getAllQuestion()).thenReturn(expected);

        // Act
        List<Question> result = getAllQuestionUsecase.execute();

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(i+1, result.get(i).id());
        }
    }
}