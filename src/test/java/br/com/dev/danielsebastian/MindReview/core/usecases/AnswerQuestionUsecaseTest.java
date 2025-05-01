package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.AnswerQuestion;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerQuestionUsecaseTest {
    @Mock
    private QuestionGateway gateway;

    private AnswerQuestionUsecase answerQuestionUsecase;

    @BeforeEach
    void setUp() {
        answerQuestionUsecase = new AnswerQuestionUsecaseImpl(gateway);
    }

    @Test
    void answerQuestionDataFoundedResponseCorrect() {
        // Arrange
        LocalDateTime timeBefore = LocalDateTime.now().minusDays(1);
        Question expectedGetById = new Question(
                1L,
                "Test text",
                "Test response",
                DifficultyQuestion.EASY,
                timeBefore,
                TimeDelay.NOW,
                5,
                true
        );

        Question expectedUpdateTimeDaleyAndTimeDo = new Question(
                1L,
                "Test text",
                "Test response",
                DifficultyQuestion.EASY,
                LocalDateTime.now(),
                TimeDelay.DAY,
                5,
                false
        );

        AnswerQuestion input = new AnswerQuestion("Test response");

        when(gateway.getQuestionById(1L)).thenReturn(Optional.of(expectedGetById));
        when(gateway.updateQuestionTimeDalyAndTimeDo(any(Question.class))).thenReturn(expectedUpdateTimeDaleyAndTimeDo);

        //Act
        Question result = answerQuestionUsecase.execute(1L, input);

        assertEquals(expectedUpdateTimeDaleyAndTimeDo.id(), result.id());
        assertTrue(expectedGetById.timeDo().isBefore(result.timeDo()) || expectedGetById.timeDo().isEqual(result.timeDo()));
        assertEquals(expectedUpdateTimeDaleyAndTimeDo.timeDelay(), result.timeDelay());
        assertFalse(result.isNeedReview());
    }

    @Test
    void answerQuestionDataFoundedResponseIncorrect() {
        // Arrange
        LocalDateTime timeBefore = LocalDateTime.now().minusDays(1);
        Question expectedGetById = new Question(
                1L,
                "Test text",
                "Test response",
                DifficultyQuestion.EASY,
                timeBefore,
                TimeDelay.NOW,
                5,
                true
        );

        AnswerQuestion input = new AnswerQuestion("Incorrect");

        when(gateway.getQuestionById(1L)).thenReturn(Optional.of(expectedGetById));

        //Act
        Question result = answerQuestionUsecase.execute(1L, input);

        assertEquals(expectedGetById.id(), result.id());
        assertTrue(timeBefore.isEqual(result.timeDo()));
        assertEquals(TimeDelay.NOW, result.timeDelay());
        assertTrue(result.isNeedReview());
    }

    @Test
    void answerQuestionDataNotFounded() {
        // Arrange
        AnswerQuestion input = new AnswerQuestion("Incorrect");

        when(gateway.getQuestionById(1L)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(DataNotFoundException.class, () -> answerQuestionUsecase.execute(1L, input));
    }
}