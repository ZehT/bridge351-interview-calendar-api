package com.bridge351.interviewcalendarapi.slot.exception;

import com.bridge351.interviewcalendarapi.commons.exceptions.NotFoundException;

public class SlotsByUserNotFoundException extends NotFoundException {

    private static final long serialVersionUID = 1501381976756693469L;
    private static final String MSG = "slot.exception.not.found.by.user";

    public SlotsByUserNotFoundException() {
        super(MSG);
    }

}
