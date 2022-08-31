package com.bridge351.interviewcalendarapi.user.exception;

import com.bridge351.interviewcalendarapi.commons.exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    private static final long serialVersionUID = 7964684639413783157L;
    private static final String MSG = "user.exception.not.found";

    public UserNotFoundException() {
        super(MSG);
    }

    public UserNotFoundException(final String msg) {
        super(msg);
    }

}
