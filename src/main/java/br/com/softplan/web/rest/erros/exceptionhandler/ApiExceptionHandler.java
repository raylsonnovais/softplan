package br.com.softplan.web.rest.erros.exceptionhandler;

import br.com.softplan.web.rest.erros.exceptionhandler.exception.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException e) {
        ApiError apiError = ApiError.builder()
                .dateTime(LocalDateTime.now())
                .fieldErrors(Collections.singletonMap("message", e.getMessage()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        ApiError apiError = ApiError.builder()
                .dateTime(LocalDateTime.now())
                .fieldErrors(Collections.singletonMap("message", "Erro não tratado")) // Use a Map for consistency
                .fieldErrors(Collections.singletonMap("detail", e.getMessage())) // Use a Map for consistency
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiError);
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {


        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage
                ));

        ApiError apiError = ApiError.builder()
                .dateTime(LocalDateTime.now())
                .fieldErrors(fieldErrors)
                .build();


        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }


}

