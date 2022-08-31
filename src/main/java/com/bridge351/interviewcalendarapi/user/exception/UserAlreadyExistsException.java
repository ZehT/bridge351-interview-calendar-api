package com.bridge351.interviewcalendarapi.user.exception;

import com.bridge351.interviewcalendarapi.commons.exceptions.BusinessException;

public class UserAlreadyExistsException extends BusinessException {

    private static final long serialVersionUID = 5154002017155655866L;
    private static final String MSG = "user.exception.user.already.exists";

    public UserAlreadyExistsException() {
        super(MSG);
    }

}
