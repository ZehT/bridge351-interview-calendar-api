package com.bridge351.interviewcalendarapi.slot.exception;

import com.bridge351.interviewcalendarapi.commons.exceptions.BusinessException;

public class SlotInvalidUserException extends BusinessException {

    private static final long serialVersionUID = 7964684639413783157L;
    private static final String MSG = "slot.exception.invalid.user";

    public SlotInvalidUserException() {
        super(MSG);
    }

}
