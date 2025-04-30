package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;
import br.com.dev.danielsebastian.MindReview.infra.exeception.DataNotFoundException;
import br.com.dev.danielsebastian.MindReview.infra.gateway.QuestionRepositoryGateway;
import br.com.dev.danielsebastian.MindReview.infra.mappers.QuestionEntityMapper;
import br.com.dev.danielsebastian.MindReview.infra.persistence.QuestionEntity;
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
class UpdateQuestionUsecaseTest {
    @Autowired
    private QuestionRepository questionRepository;

    private QuestionEntityMapper mapper = new QuestionEntityMapper();

    private QuestionGateway gateway;

    private UpdateQuestionUsecase updateQuestionUsecase;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        gateway = new QuestionRepositoryGateway(questionRepository, mapper);
        updateQuestionUsecase = new UpdateQuestionUsecaseImpl(gateway);
    }

    @AfterEach
    void resetDB() {
        entityManager.createNativeQuery("TRUNCATE TABLE tb_question RESTART IDENTITY").executeUpdate();
    }

    @Test
    @Transactional
    void updateQuestionDataFoundedUpdateTextResponseAndDifficultyQuestion() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        this.createQuestionEntity(now);

        Question input = new Question(
                null,
                "Test text Update",
                "Test response Update",
                DifficultyQuestion.NORMAL,
                null,
                null,
                0,
                false
        );

        // Act
        Question result = updateQuestionUsecase.execute(1L, input);

        // Assert
        assertEquals(1L, result.id());
        assertEquals(input.text(), result.text());
        assertEquals(input.response(), result.response());
        assertEquals(input.difficultyQuestion(), result.difficultyQuestion());
        assertEquals(now, result.timeDo());
        assertEquals(TimeDelay.NOW, result.timeDelay());
        assertEquals(1, result.priority());
        assertTrue(result.isNeedReview());
    }

    @Test
    @Transactional
    void updateQuestionDataFoundedUpdateTextAndResponse() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        this.createQuestionEntity(now);

        Question input = new Question(
                null,
                "Test text Update",
                "Test response Update",
                null,
                null,
                null,
                0,
                false
        );

        // Act
        Question result = updateQuestionUsecase.execute(1L, input);

        // Assert
        assertEquals(1L, result.id());
        assertEquals(input.text(), result.text());
        assertEquals(input.response(), result.response());
        assertEquals(DifficultyQuestion.EASY, result.difficultyQuestion());
        assertEquals(now, result.timeDo());
        assertEquals(TimeDelay.NOW, result.timeDelay());
        assertEquals(1, result.priority());
        assertTrue(result.isNeedReview());
    }

    @Test
    @Transactional
    void updateQuestionDataFoundedUpdateTextAndDifficultyQuestion() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        this.createQuestionEntity(now);

        Question input = new Question(
                null,
                "Test text Update",
                "",
                DifficultyQuestion.NORMAL,
                null,
                null,
                0,
                false
        );

        // Act
        Question result = updateQuestionUsecase.execute(1L, input);

        // Assert
        assertEquals(1L, result.id());
        assertEquals(input.text(), result.text());
        assertEquals("response test", result.response());
        assertEquals(input.difficultyQuestion(), result.difficultyQuestion());
        assertEquals(now, result.timeDo());
        assertEquals(TimeDelay.NOW, result.timeDelay());
        assertEquals(1, result.priority());
        assertTrue(result.isNeedReview());
    }

    @Test
    @Transactional
    void updateQuestionDataFoundedUpdateResponseAndDifficultyQuestion() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        this.createQuestionEntity(now);

        Question input = new Question(
                null,
                "",
                "Test response Update",
                DifficultyQuestion.NORMAL,
                null,
                null,
                0,
                false
        );

        // Act
        Question result = updateQuestionUsecase.execute(1L, input);

        // Assert
        assertEquals(1L, result.id());
        assertEquals("text test", result.text());
        assertEquals(input.response(), result.response());
        assertEquals(input.difficultyQuestion(), result.difficultyQuestion());
        assertEquals(now, result.timeDo());
        assertEquals(TimeDelay.NOW, result.timeDelay());
        assertEquals(1, result.priority());
        assertTrue(result.isNeedReview());
    }

    @Test
    @Transactional
    void updateQuestionDataNotFounded() {
        // Arrange
        Question input = new Question(
                null,
                "Test text Update",
                "Test response Update",
                null,
                null,
                null,
                0,
                false
        );

        // Act and Assert
        assertThrows(DataNotFoundException.class, () -> updateQuestionUsecase.execute(1L, input));
    }

    private void createQuestionEntity(LocalDateTime localDateTime) {
        QuestionEntity question = new QuestionEntity();
        question.setText("text test");
        question.setResponse("response test");
        question.setDifficultyQuestion(DifficultyQuestion.EASY);
        question.setTimeDo(localDateTime);
        question.setTimeDelay(TimeDelay.NOW);
        question.setPriority(1);
        question.setNeedReview(true);
        this.entityManager.persist(question);
    }
}