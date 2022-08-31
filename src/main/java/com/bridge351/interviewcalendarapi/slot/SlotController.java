package com.bridge351.interviewcalendarapi.slot;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.slot.domain.SlotDTO;
import com.bridge351.interviewcalendarapi.slot.domain.SlotEntity;
import com.bridge351.interviewcalendarapi.slot.domain.SlotFilterDTO;
import com.bridge351.interviewcalendarapi.slot.domain.SlotRequestDTO;
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
    public BasicResponse<List<SlotDTO>> findSlotsByUser(final Long userId) {
        final List<SlotEntity> slots = this.slotService.findSlotsByUserId(userId);
        return BasicResponse.withData(slots.stream()
                .map(SlotDTO::ofEntity)
                .collect(Collectors.toList())
        );
    }

    @Override
    public BasicResponse<List<SlotDTO>> findMatchedSlots(final SlotFilterDTO slotFilter) {
        final List<SlotEntity> matchedSlots = this.slotService.findMatchedSlots(slotFilter);
        return BasicResponse.withData(matchedSlots.stream()
                .map(SlotDTO::ofEntity)
                .collect(Collectors.toList())
        );
    }

    @Override
    public BasicResponse<Void> addSlots(final SlotRequestDTO slotRequest) {
        slotRequest.getSlotDateTimeList().forEach(slotDateTimeDTO ->
                this.slotService.addSlot(SlotEntity.ofSlotWithDateAndTime(slotRequest.getUserId(), slotDateTimeDTO)));
        return BasicResponse.ok(this.messageSource.getMessage("slot.added", null, Locale.getDefault())
        );
    }

}
