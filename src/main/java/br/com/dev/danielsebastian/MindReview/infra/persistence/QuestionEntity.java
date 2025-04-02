package br.com.dev.danielsebastian.MindReview.infra.persistence;

import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;
import br.com.dev.danielsebastian.MindReview.core.enuns.TimeDelay;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String response;

    @Column(name = "difficulty_question", nullable = false)
    @Enumerated(EnumType.STRING)
    private DifficultyQuestion difficultyQuestion;

    @Column(nullable = false, name = "time_do")
    private LocalDateTime timeDo;

    @Column(name = "time_delay", nullable = false)
    @Enumerated(EnumType.STRING)
    private TimeDelay timeDelay;

    @Column(nullable = false)
    private int priority;

    @Column(name = "is_need_review", nullable = false, columnDefinition="boolean default true")
    boolean isNeedReview;
}
