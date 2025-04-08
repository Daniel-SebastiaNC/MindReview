package br.com.dev.danielsebastian.MindReview.core.gateway;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;
import br.com.dev.danielsebastian.MindReview.infra.mappers.QuestionEntityMapper;
import br.com.dev.danielsebastian.MindReview.infra.persistence.QuestionRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

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
        assertEquals(question1.id(), allQuestion.get(0).id());
        assertEquals(question2.id(), allQuestion.get(1).id());
    }

    @Test
    void getQuestionById() {
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
                LocalDateTime.now(),
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