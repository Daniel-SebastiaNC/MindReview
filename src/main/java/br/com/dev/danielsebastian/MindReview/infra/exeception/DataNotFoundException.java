package br.com.dev.danielsebastian.MindReview.infra.exeception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
