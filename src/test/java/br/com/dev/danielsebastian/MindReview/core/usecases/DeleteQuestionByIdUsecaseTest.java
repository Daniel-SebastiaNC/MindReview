package br.com.dev.danielsebastian.MindReview.core.usecases;

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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class DeleteQuestionByIdUsecaseTest {
    @Autowired
    private QuestionRepository questionRepository;

    private QuestionEntityMapper mapper = new QuestionEntityMapper();

    private QuestionGateway gateway;

    private DeleteQuestionByIdUsecase deleteQuestionByIdUsecase;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        gateway = new QuestionRepositoryGateway(questionRepository, mapper);
        deleteQuestionByIdUsecase = new DeleteQuestionByIdUsecaseImpl(gateway);
    }

    @AfterEach
    void resetDB() {
        entityManager.createNativeQuery("TRUNCATE TABLE tb_question RESTART IDENTITY").executeUpdate();
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

}