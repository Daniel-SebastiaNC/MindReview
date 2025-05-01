package br.com.dev.danielsebastian.MindReview.core.usecases;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class GetAllQuestionNeedReviewUsecaseTest {

    @Autowired
    private QuestionRepository questionRepository;

    private QuestionEntityMapper mapper = new QuestionEntityMapper();

    private QuestionGateway gateway;

    private UpdateQuestionUsecase updateQuestionUsecase;

    @Autowired
    private EntityManager entityManager;

    private GetAllQuestionNeedReviewUsecase getAllQuestionNeedReviewUsecase;

    @BeforeEach
    void setUp() {
        gateway = new QuestionRepositoryGateway(questionRepository, mapper);
        getAllQuestionNeedReviewUsecase = new GetAllQuestionNeedReviewUsecaseImpl(gateway);
    }

    @AfterEach
    void resetDB() {
        entityManager.createNativeQuery("TRUNCATE TABLE tb_question RESTART IDENTITY").executeUpdate();
    }

    private void createQuestionEntity(LocalDateTime localDateTime, TimeDelay timeDelay, boolean isNeedReview) {
        QuestionEntity question = new QuestionEntity();
        question.setText("text test");
        question.setResponse("response test");
        question.setDifficultyQuestion(DifficultyQuestion.EASY);
        question.setTimeDo(localDateTime);
        question.setTimeDelay(timeDelay);
        question.setPriority(5);
        question.setNeedReview(isNeedReview);
        this.entityManager.persist(question);
    }
}