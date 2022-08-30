package com.bridge351.interviewcalendarapi.slot.exception;

import com.bridge351.interviewcalendarapi.commons.exceptions.BusinessException;

public class SlotInvalidException extends BusinessException {

    private static final long serialVersionUID = 4755932215692784187L;
    private static final String MSG = "slot.exception.invalid.already.in.use";

    public SlotInvalidException() {
        super(MSG);
    }

}
