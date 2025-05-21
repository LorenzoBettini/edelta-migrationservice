package it.gssi.edelta.migrationservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import it.gssi.edelta.migrationservice.api.MigrationException;

@RestControllerAdvice("it.gssi.edelta.migrationservice.api")
public class RESTExceptionHandler {

    @ExceptionHandler(MigrationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String migrationExceptionHandler(MigrationException ex) {
        return ex.getMessage();
    }
}
