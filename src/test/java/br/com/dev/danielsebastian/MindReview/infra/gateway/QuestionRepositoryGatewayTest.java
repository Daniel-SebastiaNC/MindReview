package br.com.dev.danielsebastian.MindReview.infra.gateway;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;
import br.com.dev.danielsebastian.MindReview.infra.mappers.QuestionEntityMapper;
import br.com.dev.danielsebastian.MindReview.infra.persistence.QuestionEntity;
import br.com.dev.danielsebastian.MindReview.infra.persistence.QuestionRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

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

    @Test
    void saveQuestionSuccess() {
        Question question = this.createQuestionEntity();

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
    void getAllQuestion() {
    }

    @Test
    void getQuestionById() {
    }

    @Test
    void deleteQuestionById() {
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

    private Question createQuestionEntity() {
        return new Question(null,"text test", "response test", DifficultyQuestion.EASY, LocalDateTime.now(), TimeDelay.NOW, 1, true);
    }
}