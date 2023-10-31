package br.com.softplan.web.rest.erros.exceptionhandler.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
