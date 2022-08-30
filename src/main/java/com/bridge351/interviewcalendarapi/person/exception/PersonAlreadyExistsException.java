package com.bridge351.interviewcalendarapi.person.exception;

import com.bridge351.interviewcalendarapi.commons.exceptions.BusinessException;

public class PersonAlreadyExistsException extends BusinessException {

    private static final long serialVersionUID = 5154002017155655866L;
    private static final String MSG = "person.exception.person.already.exists";

    public PersonAlreadyExistsException() {
        super(MSG);
    }

}
