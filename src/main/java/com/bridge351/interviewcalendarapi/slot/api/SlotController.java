package com.bridge351.interviewcalendarapi.slot.api;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.slot.SlotService;
import com.bridge351.interviewcalendarapi.slot.domain.SlotDTO;
import com.bridge351.interviewcalendarapi.slot.domain.SlotEntity;
import com.bridge351.interviewcalendarapi.slot.domain.SlotFilterDTO;
import com.bridge351.interviewcalendarapi.slot.domain.SlotSimpleDTO;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class SlotController implements SlotAPI {

    private final SlotService slotService;
    private final MessageSource messageSource;

    public SlotController(final SlotService slotService, final MessageSource messageSource) {
        this.slotService = slotService;
        this.messageSource = messageSource;
    }

    @Override
    public BasicResponse<List<SlotSimpleDTO>> findMatchedSlots(final SlotFilterDTO slotFilterDTO) {
        final List<SlotEntity> matchedSlots = this.slotService.findMatchedSlots(slotFilterDTO);
        return BasicResponse.withData(matchedSlots.stream()
                .map(SlotSimpleDTO::ofEntity)
                .collect(Collectors.toList())
        );
    }

    @Override
    public BasicResponse<SlotDTO> addSlot(final SlotDTO slotDTO) {
        final SlotEntity slotEntity = this.slotService.addSlot(SlotEntity.ofDTO(slotDTO));
        return BasicResponse.withDataAndMessage(
                SlotSimpleDTO.ofEntity(slotEntity),
                this.messageSource.getMessage("slot.added", null, Locale.getDefault())
        );
    }

}
