package com.bridge351.interviewcalendarapi.slot;

import com.bridge351.interviewcalendarapi.slot.domain.SlotEntity;
import com.bridge351.interviewcalendarapi.slot.domain.SlotFilterDTO;
import com.bridge351.interviewcalendarapi.slot.exception.SlotInvalidException;
import com.bridge351.interviewcalendarapi.slot.exception.SlotInvalidUserException;
import com.bridge351.interviewcalendarapi.slot.exception.SlotMatchedNotFoundException;
import com.bridge351.interviewcalendarapi.slot.exception.SlotsByUserNotFoundException;
import com.bridge351.interviewcalendarapi.user.UserService;
import com.bridge351.interviewcalendarapi.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SlotService {

    private final SlotRepository slotRepository;
    private final UserService userService;

    public SlotService(final SlotRepository slotRepository, final UserService userService) {
        this.slotRepository = slotRepository;
        this.userService = userService;
    }

    public List<SlotEntity> findSlotsByUserId(final Long userId) {
        final List<SlotEntity> slots = this.slotRepository.findSlotByUserId(userId);
        if (CollectionUtils.isEmpty(slots)) {
            throw new SlotsByUserNotFoundException();
        }
        return slots;
    }

    @Transactional
    public void addSlot(final SlotEntity slotEntity) {
        validateUser(slotEntity.getUser().getId());
        validateTime(slotEntity);
        this.slotRepository.save(slotEntity);
    }

    private void validateUser(final Long userId) {
        try {
            this.userService.findUserById(userId);
        } catch (final UserNotFoundException ex) {
            throw new SlotInvalidUserException();
        }
    }

    private void validateTime(final SlotEntity slotEntity) {
        this.slotRepository.findSlotByUserIdAndSlotDateAndSlotHour(slotEntity.getUser().getId(), slotEntity.getSlotDate(), slotEntity.getSlotHour())
                .ifPresent(user -> {
                    throw new SlotInvalidException();
                });
    }

    public List<SlotEntity> findMatchedSlots(final SlotFilterDTO slotFilter) {
        final List<SlotEntity> matchedSlots = this.slotRepository.findMatchedSlots(slotFilter.getCandidateId(), slotFilter.getInterviewersId());
        if (CollectionUtils.isEmpty(matchedSlots)) {
            throw new SlotMatchedNotFoundException();
        }
        return matchedSlots;
    }

}
