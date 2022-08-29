package com.bridge351.interviewcalendarapi.config;

import com.bridge351.interviewcalendarapi.commons.exceptions.NotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdviceExceptionHandler {

    private final MessageSource messageSource;

    public ControllerAdviceExceptionHandler(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<BasicResponse<Object>> notFoundException(final NotFoundException notFoundException) {
        final BasicResponse<Object> basicResponse = BasicResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(this.messageSource.getMessage(notFoundException.getMessage(), null, Locale.getDefault()))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(basicResponse);
    }

}
