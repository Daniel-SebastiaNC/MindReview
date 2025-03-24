package br.com.dev.danielsebastian.MindReview.infra.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlerDataNotFoundException(DataNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("Error: ", ex.getMessage());
        error.put("Message: ", "Please, insert a valid value for your question and try again");
        return error;
    }
}
