package br.com.dev.danielsebastian.MindReview.infra.beans;

import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;
import br.com.dev.danielsebastian.MindReview.core.usecases.CreateQuestionUsecase;
import br.com.dev.danielsebastian.MindReview.core.usecases.CreateQuestionUsecaseImpl;
import br.com.dev.danielsebastian.MindReview.core.usecases.GetAllQuestionUsecase;
import br.com.dev.danielsebastian.MindReview.core.usecases.GetAllQuestionUsecaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateQuestionUsecase createQuestionUsecase(QuestionGateway questionGateway){
        return new CreateQuestionUsecaseImpl(questionGateway);
    }

    @Bean
    public GetAllQuestionUsecase getAllQuestionUsecase(QuestionGateway questionGateway){
        return new GetAllQuestionUsecaseImpl(questionGateway);
    }
}
