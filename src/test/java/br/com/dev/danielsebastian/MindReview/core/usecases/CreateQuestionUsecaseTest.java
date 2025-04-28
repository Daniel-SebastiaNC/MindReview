package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;
import br.com.dev.danielsebastian.MindReview.infra.gateway.QuestionRepositoryGateway;
import br.com.dev.danielsebastian.MindReview.infra.mappers.QuestionEntityMapper;
import br.com.dev.danielsebastian.MindReview.infra.persistence.QuestionRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CreateQuestionUsecaseTest {
    @Autowired
    private QuestionRepository questionRepository;

    private QuestionEntityMapper mapper = new QuestionEntityMapper();

    private QuestionGateway gateway;

    private CreateQuestionUsecase createQuestionUsecase;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        gateway = new QuestionRepositoryGateway(questionRepository, mapper);
        createQuestionUsecase = new CreateQuestionUsecaseImpl(gateway);
    }

    @AfterEach
    void resetDB() {
        entityManager.createNativeQuery("TRUNCATE TABLE tb_question RESTART IDENTITY").executeUpdate();
    }

    @Test
    @Transactional
    void createQuestionSuccess() {
        // Arrange
        Question input = new Question(
                null,
                "Test text",
                "Test response",
                DifficultyQuestion.EASY,
                null,
                null,
                0,
                false
        );

        LocalDateTime momentSave = LocalDateTime.now();

        // Act
        Question result = createQuestionUsecase.execute(input);

        // Assert
        assertNotNull(result);
        assertNotNull(result.id());
        assertEquals("Test text", result.text());
        assertEquals("Test response", result.response());
        assertEquals(DifficultyQuestion.EASY, result.difficultyQuestion());
        assertEquals(5, result.priority());
        assertEquals(TimeDelay.NOW, result.timeDelay());
        assertEquals(momentSave, result.timeDo());
        assertTrue(result.isNeedReview());


        // E podemos confirmar que está no banco:
        assertEquals(1, questionRepository.findAll().size());
    }
}