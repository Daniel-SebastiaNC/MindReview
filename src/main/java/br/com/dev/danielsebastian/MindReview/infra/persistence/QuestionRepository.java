package br.com.dev.danielsebastian.MindReview.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findAllByIsNeedReviewOrderByPriorityAsc(boolean b);

}
