package com.bridge351.interviewcalendarapi.slot.exception;

import com.bridge351.interviewcalendarapi.commons.exceptions.NotFoundException;

public class SlotMatchedNotFoundException extends NotFoundException {

    private static final long serialVersionUID = -506945522688201053L;
    private static final String MSG = "slot.exception.matched.not.found";

    public SlotMatchedNotFoundException() {
        super(MSG);
    }

}
