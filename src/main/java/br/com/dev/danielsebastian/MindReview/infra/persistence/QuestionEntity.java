package br.com.dev.danielsebastian.MindReview.infra.persistence;

import br.com.dev.danielsebastian.MindReview.core.enuns.DifficultyQuestion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
