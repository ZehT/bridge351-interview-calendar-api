package com.bridge351.interviewcalendarapi.slot.exception;

import com.bridge351.interviewcalendarapi.commons.exceptions.BusinessException;

public class SlotException extends BusinessException {

    private static final long serialVersionUID = 7964684639413783157L;

    public SlotException(final String msg) {
        super(msg);
    }

}
