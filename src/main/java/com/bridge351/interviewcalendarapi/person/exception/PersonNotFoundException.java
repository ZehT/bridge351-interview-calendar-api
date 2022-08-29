package com.bridge351.interviewcalendarapi.person.exception;

import com.bridge351.interviewcalendarapi.commons.exceptions.NotFoundException;

public class PersonNotFoundException extends NotFoundException {

    private static final long serialVersionUID = 7964684639413783157L;
    private static final String MSG = "person.exception.not.found";

    public PersonNotFoundException() {
        super(MSG);
    }

}