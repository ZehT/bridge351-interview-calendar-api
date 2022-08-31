package com.bridge351.interviewcalendarapi.config;

import com.bridge351.interviewcalendarapi.commons.exceptions.BusinessException;
import com.bridge351.interviewcalendarapi.commons.exceptions.NotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestControllerAdviceExceptionHandler {

    private final MessageSource messageSource;

    public RestControllerAdviceExceptionHandler(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<BasicResponse<Void>> notFoundException(final NotFoundException notFoundException) {
        final BasicResponse<Void> basicResponse = BasicResponse.fail(HttpStatus.OK.value(),
                this.messageSource.getMessage(notFoundException.getMessage(), null, Locale.getDefault()));
        return ResponseEntity.status(HttpStatus.OK).body(basicResponse);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<BasicResponse<Void>> businessException(final BusinessException businessException) {
        final BasicResponse<Void> basicResponse = BasicResponse.fail(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                this.messageSource.getMessage(businessException.getMessage(), null, Locale.getDefault()));
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(basicResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BasicResponse<Map<String, String>>> handleValidationExceptions(final MethodArgumentNotValidException ex) {
        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            final String fieldName = ((FieldError) error).getField();
            final String errorMessage = this.messageSource.getMessage(error.getDefaultMessage(), null, Locale.getDefault());
            errors.put(fieldName, errorMessage);
        });
        final BasicResponse<Map<String, String>> basicResponse = BasicResponse.fail(HttpStatus.BAD_REQUEST.value(),
                this.messageSource.getMessage("commons.validation.mandatory.fields", null, Locale.getDefault()),
                errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(basicResponse);
    }

}
