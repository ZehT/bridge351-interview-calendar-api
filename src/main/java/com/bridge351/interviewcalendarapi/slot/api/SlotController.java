package com.bridge351.interviewcalendarapi.slot.api;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.slot.SlotService;
import com.bridge351.interviewcalendarapi.slot.domain.SlotDTO;
import com.bridge351.interviewcalendarapi.slot.domain.SlotEntity;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
public class SlotController implements SlotAPI {

    private final SlotService slotService;
    private final MessageSource messageSource;

    public SlotController(final SlotService slotService, final MessageSource messageSource) {
        this.slotService = slotService;
        this.messageSource = messageSource;
    }

    @Override
    public BasicResponse<List<SlotDTO>> findSlots() {
        return null;
    }

    @Override
    public BasicResponse<SlotDTO> addSlot(final SlotDTO slotDTO) {
        final SlotEntity slotEntity = this.slotService.addSlot(SlotEntity.ofDTO(slotDTO));
        return BasicResponse.withDataAndMessage(
                SlotDTO.ofEntity(slotEntity),
                this.messageSource.getMessage("slot.added", null, Locale.getDefault())
        );
    }

}
