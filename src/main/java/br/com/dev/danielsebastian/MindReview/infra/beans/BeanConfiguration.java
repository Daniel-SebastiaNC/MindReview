package br.com.dev.danielsebastian.MindReview.infra.beans;

import br.com.dev.danielsebastian.MindReview.core.gateway.QuestionGateway;
import br.com.dev.danielsebastian.MindReview.core.usecases.*;
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

    @Bean
    public GetQuestionByIdUsecase getQuestionByIdUsecase(QuestionGateway questionGateway){
        return new GetQuestionByIdUsecaseImpl(questionGateway);
    }

    @Bean
    public DeleteQuestionByIdUsecase deleteQuestionByIdUsecase(QuestionGateway questionGateway){
        return new DeleteQuestionByIdUsecaseImpl(questionGateway);
    }

    @Bean
    public UpdateQuestionUsecase updateQuestionUsecase(QuestionGateway questionGateway){
        return new UpdateQuestionUsecaseImpl(questionGateway);
    }

    @Bean
    public GetAllQuestionNeedReviewUsecase getAllQuestionNeedReview(QuestionGateway questionGateway){
        return new GetAllQuestionNeedReviewUsecaseImpl(questionGateway);
    }

    @Bean
    public AnswerQuestionUsecase answerQuestionUsecase(QuestionGateway questionGateway){
        return new AnswerQuestionUsecaseImpl(questionGateway);
    }
}
