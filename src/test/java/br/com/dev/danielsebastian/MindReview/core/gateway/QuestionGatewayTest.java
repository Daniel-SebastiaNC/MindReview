package br.com.dev.danielsebastian.MindReview.core.gateway;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;
import br.com.dev.danielsebastian.MindReview.infra.mappers.QuestionEntityMapper;
import br.com.dev.danielsebastian.MindReview.infra.persistence.QuestionRepository;
import jakarta.persistence.EntityManager;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
class QuestionGatewayTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    QuestionEntityMapper mapper;

    @Autowired
    QuestionGateway gateway;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("should create question with default values")
    void createQuestionWithDefaultValues() {
        // Arrange
        Question input = new Question(
                null,
                "text test",
                "response test",
                DifficultyQuestion.EASY,
                null,
                null,
                0,
                false
        );

        // Act
        var now = LocalDateTime.now();
        Question result = gateway.createQuestion(input);

        // Assert
        assertNotNull(result);
        assertNotNull(result.id()); // deve ter ID gerado
        assertEquals(now, result.timeDo());
        assertEquals(TimeDelay.NOW, result.timeDelay());
        assertEquals(5, result.priority());
        assertTrue(result.isNeedReview());

        questionRepository.deleteAll();
    }

    @Test
    @DisplayName("should return all saved questions")
    void getAllQuestion() {
        // Arrange
        Question question1 = persistQuestion(TimeDelay.NOW);
        Question question2 = persistQuestion(TimeDelay.DAY);
        // Act
        List<Question> allQuestion = gateway.getAllQuestion();

        // Assert
        assertEquals(2, allQuestion.size());
        assertEquals(question1, allQuestion.get(0));
        assertEquals(question2, allQuestion.get(1));
    }

    @Test
    @DisplayName("should return question when ID exists")
    void getQuestionByIdIsPresent() {
        // Arrange
        Question question1 = persistQuestion(TimeDelay.NOW);

        // Act
        Optional<Question> questionById = gateway.getQuestionById(question1.id());

        // Assert
        assertTrue(questionById.isPresent());
        assertEquals(question1, questionById.get());
    }

    @Test
    @DisplayName("should return empty when ID does not exist")
    void getQuestionByIdIsNull() {
        // Arrange
        Question question1 = persistQuestion(TimeDelay.NOW);

        // Act
        Optional<Question> questionById = gateway.getQuestionById(0L);

        // Assert
        assertTrue(questionById.isEmpty());
    }

    @Test
    void deleteQuestionById() {
    }

    @Test
    void updateQuestion() {
    }

    @Test
    void updateQuestionPriority() {
    }

    @Test
    void getAllQuestionNeedReview() {
    }

    @Test
    void updateQuestionTimeDalyAndTimeDo() {
    }

    private Question persistQuestion(TimeDelay timeDelay){
        Question input = new Question(
                null,
                "text test",
                "response test",
                DifficultyQuestion.EASY,
                LocalDateTime.of(2025, 4, 8, 10, 17, 54, 788389000),
                timeDelay,
                0,
                false
        );

        return mapper.toDomain(
                questionRepository.save(
                        mapper.toEntity(input)
                )
        );
    }
}