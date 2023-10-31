package br.com.softplan.web.rest.erros.exceptionhandler;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ApiError {

    private LocalDateTime dateTime;
    private Map<String,String> fieldErrors;

}
