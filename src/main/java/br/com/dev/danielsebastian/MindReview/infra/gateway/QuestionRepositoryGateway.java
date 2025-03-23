package br.com.dev.danielsebastian.MindReview.infra.gateway;

import br.com.dev.danielsebastian.MindReview.core.domians.Question;
import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;
import br.com.dev.danielsebastian.MindReview.infra.mappers.QuestionEntityMapper;
import br.com.dev.danielsebastian.MindReview.infra.persistence.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionRepositoryGateway implements QuestionGateway {

    private final QuestionRepository questionRepository;
    private final QuestionEntityMapper questionEntityMapper;

    @Override
    public Question createQuestion(Question question) {
        return questionEntityMapper.toDomain(
                questionRepository.save(
                        questionEntityMapper.toEntity(question)
                )
        );
    }

    @Override
    public List<Question> getAllQuestion() {
        return questionRepository.findAll().stream()
                .map(questionEntityMapper::toDomain)
                .toList();
    }
}
