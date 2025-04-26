package br.com.dev.danielsebastian.MindReview.infra.persistence;

import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class QuestionRepositoryTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void findAllByIsNeedReviewOrderByPriorityAscDataFounded() {

        this.createQuestionEntity(true, 2);
        this.createQuestionEntity(true, 1);
        this.createQuestionEntity(false, 1);


        List<QuestionEntity> allByIsNeedReviewOrderByPriorityAsc = questionRepository.findAllByIsNeedReviewOrderByPriorityAsc(true);

        assertEquals(2, allByIsNeedReviewOrderByPriorityAsc.size());
        assertEquals(1, allByIsNeedReviewOrderByPriorityAsc.get(0).getPriority());
        assertEquals(2, allByIsNeedReviewOrderByPriorityAsc.get(1).getPriority());
    }

    @Test
    void findAllByIsNeedReviewOrderByPriorityAscDataNotFounded() {
        List<QuestionEntity> allByIsNeedReviewOrderByPriorityAsc = questionRepository.findAllByIsNeedReviewOrderByPriorityAsc(true);

        assertTrue(allByIsNeedReviewOrderByPriorityAsc.isEmpty());
    }

    private void createQuestionEntity(boolean b, int priority) {
        QuestionEntity question = new QuestionEntity();
        question.setText("text test");
        question.setResponse("response test");
        question.setDifficultyQuestion(DifficultyQuestion.EASY);
        question.setTimeDo(LocalDateTime.now());
        question.setTimeDelay(TimeDelay.NOW);
        question.setPriority(priority);
        question.setNeedReview(b);
        this.entityManager.persist(question);
    }
}