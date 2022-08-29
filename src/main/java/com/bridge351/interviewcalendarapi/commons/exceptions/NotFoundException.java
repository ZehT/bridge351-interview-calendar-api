package com.bridge351.interviewcalendarapi.commons.exceptions;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7514980849614262821L;

    public NotFoundException(final String errorMsg) {
        super(errorMsg);
    }

}
