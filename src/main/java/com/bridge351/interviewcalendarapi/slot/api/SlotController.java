package com.bridge351.interviewcalendarapi.slot.api;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.slot.domain.SlotDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SlotController implements SlotAPI {

    @Override
    public BasicResponse<List<SlotDTO>> findSlots() {
        return null;
    }

    @Override
    public BasicResponse<SlotDTO> addSlot(final SlotDTO slotDTO) {
        return null;
    }

}
