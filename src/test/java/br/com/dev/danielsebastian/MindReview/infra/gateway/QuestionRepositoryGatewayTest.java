package br.com.dev.danielsebastian.MindReview.infra.gateway;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;
import br.com.dev.danielsebastian.MindReview.infra.persistence.QuestionEntity;
import br.com.dev.danielsebastian.MindReview.infra.persistence.QuestionRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
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
class QuestionRepositoryGatewayTest {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionRepositoryGateway questionRepositoryGateway;

    @Autowired
    EntityManager entityManager;

    @AfterEach
    void resetDB() {
        entityManager.createNativeQuery("TRUNCATE TABLE tb_question RESTART IDENTITY").executeUpdate();
    }

    @Test
    @Transactional
    void saveQuestionSuccess() {
        Question question = this.createQuestion();

        Question questionSave = questionRepositoryGateway.saveQuestion(question);

        assertEquals(1, questionRepository.findAll().size());
        assertEquals(1, questionSave.id());
        assertEquals(question.text(), questionSave.text());
        assertEquals(question.response(), questionSave.response());
        assertEquals(question.difficultyQuestion(), questionSave.difficultyQuestion());
        assertEquals(question.timeDo(), questionSave.timeDo());
        assertEquals(question.timeDelay(), questionSave.timeDelay());
        assertEquals(question.priority(), questionSave.priority());
        assertEquals(question.isNeedReview(), questionSave.isNeedReview());
    }

    @Test
    @Transactional
    void getAllQuestionFoundedData() {
        this.createQuestionEntity();
        this.createQuestionEntity();
        this.createQuestionEntity();

        List<Question> allQuestion = questionRepositoryGateway.getAllQuestion();

        assertEquals(3, allQuestion.size());
        assertEquals(1, allQuestion.get(0).id());
        assertEquals(2, allQuestion.get(1).id());
        assertEquals(3, allQuestion.get(2).id());
    }

    @Test
    @Transactional
    void getAllQuestionNotFoundedData() {
        List<Question> allQuestion = questionRepositoryGateway.getAllQuestion();

        assertTrue(allQuestion.isEmpty());
    }

    @Test
    @Transactional
    void getQuestionByIdDataFounded() {
        this.createQuestionEntity();

        Optional<Question> questionById = questionRepositoryGateway.getQuestionById(1L);

        assertTrue(questionById.isPresent());
        assertEquals(1, questionById.get().id());
    }

    @Test
    @Transactional
    void getQuestionByIdDataNotFounded() {
        Optional<Question> questionById = questionRepositoryGateway.getQuestionById(1L);

        assertTrue(questionById.isEmpty());
    }

    @Test
    @Transactional
    void deleteQuestionById() {
        this.createQuestionEntity();

        Optional<Question> questionById = questionRepositoryGateway.getQuestionById(1L);

        questionRepositoryGateway.deleteQuestionById(questionById.get());

        questionById = questionRepositoryGateway.getQuestionById(1L);

        assertTrue(questionById.isEmpty());
    }

    @Test
    @Transactional
    void updateQuestionPriorityTimePassed() {
        this.createQuestionEntity(LocalDateTime.now().minusDays(1));
        Optional<Question> questionById = questionRepositoryGateway.getQuestionById(1L);

        questionRepositoryGateway.updateQuestionPriority(questionById.get(), 2, 0);

        questionById = questionRepositoryGateway.getQuestionById(1L);

        assertEquals(2, questionById.get().priority());
    }

    @Test
    @Transactional
    void updateQuestionPriorityTimeNotPassed() {
        this.createQuestionEntity(LocalDateTime.now().minusDays(1));
        Optional<Question> questionById = questionRepositoryGateway.getQuestionById(1L);

        questionRepositoryGateway.updateQuestionPriority(questionById.get(), 2, 3);

        questionById = questionRepositoryGateway.getQuestionById(1L);

        assertEquals(1, questionById.get().priority());
    }

    @Test
    @Transactional
    void getAllQuestionNeedReviewDataFounded() {
        this.createQuestionEntity(true);
        this.createQuestionEntity(false);
        this.createQuestionEntity(true);

        List<Question> allQuestionNeedReview = questionRepositoryGateway.getAllQuestionNeedReview();

        assertEquals(2, allQuestionNeedReview.size());
        assertEquals(1, allQuestionNeedReview.get(0).id());
        assertTrue(allQuestionNeedReview.get(0).isNeedReview());
        assertEquals(3, allQuestionNeedReview.get(1).id());
        assertTrue(allQuestionNeedReview.get(1).isNeedReview());
    }

    @Test
    @Transactional
    void getAllQuestionNeedReviewDataNotFounded() {
        List<Question> allQuestionNeedReview = questionRepositoryGateway.getAllQuestionNeedReview();

        assertTrue(allQuestionNeedReview.isEmpty());
    }


    @Test
    @Transactional
    void updateQuestionTimeDalyAndTimeDo() {
        this.createQuestionEntity(TimeDelay.NOW);
        this.createQuestionEntity(TimeDelay.DAY);
        this.createQuestionEntity(TimeDelay.ONE_WEEK);
        this.createQuestionEntity(TimeDelay.TWO_WEEK);
        this.createQuestionEntity(TimeDelay.MONTH);

        List<Question> allQuestion = questionRepositoryGateway.getAllQuestion();

        for (Question question : allQuestion) {
            questionRepositoryGateway.updateQuestionTimeDalyAndTimeDo(question);
        }

        List<Question> allQuestionUpdate = questionRepositoryGateway.getAllQuestion();

        for (int i = 0; i < allQuestion.size(); i++) {
            assertFalse(allQuestionUpdate.get(i).isNeedReview());
            assertTrue(allQuestionUpdate.get(i).timeDo().isAfter(allQuestion.get(i).timeDo()) || allQuestionUpdate.get(i).timeDo().isEqual(allQuestion.get(i).timeDo()));
        }

        assertEquals(TimeDelay.DAY, allQuestionUpdate.get(0).timeDelay());
        assertEquals(TimeDelay.ONE_WEEK, allQuestionUpdate.get(1).timeDelay());
        assertEquals(TimeDelay.TWO_WEEK, allQuestionUpdate.get(2).timeDelay());
        assertEquals(TimeDelay.MONTH, allQuestionUpdate.get(3).timeDelay());
        assertEquals(TimeDelay.MONTH, allQuestionUpdate.get(4).timeDelay());
    }

    private Question createQuestion() {
        return new Question(null,"text test", "response test", DifficultyQuestion.EASY, LocalDateTime.now(), TimeDelay.NOW, 1, true);
    }

    private void createQuestionEntity() {
        QuestionEntity question = new QuestionEntity();
        question.setText("text test");
        question.setResponse("response test");
        question.setDifficultyQuestion(DifficultyQuestion.EASY);
        question.setTimeDo(LocalDateTime.now());
        question.setTimeDelay(TimeDelay.NOW);
        question.setPriority(1);
        question.setNeedReview(true);
        this.entityManager.persist(question);
    }

    private void createQuestionEntity(boolean b) {
        QuestionEntity question = new QuestionEntity();
        question.setText("text test");
        question.setResponse("response test");
        question.setDifficultyQuestion(DifficultyQuestion.EASY);
        question.setTimeDo(LocalDateTime.now());
        question.setTimeDelay(TimeDelay.NOW);
        question.setPriority(1);
        question.setNeedReview(b);
        this.entityManager.persist(question);
    }

    private void createQuestionEntity(TimeDelay timeDelay) {
        QuestionEntity question = new QuestionEntity();
        question.setText("text test");
        question.setResponse("response test");
        question.setDifficultyQuestion(DifficultyQuestion.EASY);
        question.setTimeDo(LocalDateTime.now());
        question.setTimeDelay(timeDelay);
        question.setPriority(1);
        question.setNeedReview(true);
        this.entityManager.persist(question);
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