package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;
import br.com.dev.danielsebastian.MindReview.infra.exeception.DataNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetQuestionByIdUsecaseTest {
    @Mock
    private QuestionGateway gateway;

    private GetQuestionByIdUsecase getQuestionByIdUsecase;

    @BeforeEach
    void setUp() {
        getQuestionByIdUsecase = new GetQuestionByIdUsecaseImpl(gateway);
    }

    @Test
    void getQuestionByIdDataFounded() {
        // Arrange
        Question expected = new Question(
                1L,
                "Test text",
                "Test response",
                DifficultyQuestion.EASY,
                LocalDateTime.now(),
                TimeDelay.NOW,
                5,
                true
        );

        when(gateway.getQuestionById(1L)).thenReturn(Optional.of(expected));

        // Act
        Question result = getQuestionByIdUsecase.execute(1L);

        // Assert
        assertTrue(result != null);
        assertEquals(expected, result);
    }

    @Test
    void getQuestionByIdDataNotFounded() {
        // Arrange
        when(gateway.getQuestionById(1L)).thenReturn(Optional.empty());

        // Act && Assert
        assertThrows(DataNotFoundException.class, () -> getQuestionByIdUsecase.execute(1L));
    }
}