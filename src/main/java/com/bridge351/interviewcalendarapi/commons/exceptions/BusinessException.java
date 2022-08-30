package com.bridge351.interviewcalendarapi.commons.exceptions;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1515010167334795115L;

    public BusinessException(final String errorMsg) {
        super(errorMsg);
    }

}
