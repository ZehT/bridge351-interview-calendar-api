package com.bridge351.interviewcalendarapi.slot;

import com.bridge351.interviewcalendarapi.person.PersonService;
import com.bridge351.interviewcalendarapi.person.exception.PersonNotFoundException;
import com.bridge351.interviewcalendarapi.slot.domain.SlotEntity;
import com.bridge351.interviewcalendarapi.slot.domain.SlotFilterDTO;
import com.bridge351.interviewcalendarapi.slot.exception.SlotInvalidException;
import com.bridge351.interviewcalendarapi.slot.exception.SlotInvalidPersonException;
import com.bridge351.interviewcalendarapi.slot.exception.SlotMatchedNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SlotService {

    private final SlotRepository slotRepository;
    private final PersonService personService;

    public SlotService(final SlotRepository slotRepository, final PersonService personService) {
        this.slotRepository = slotRepository;
        this.personService = personService;
    }

    @Transactional
    public SlotEntity addSlot(final SlotEntity slotEntity) {
        validatePerson(slotEntity.getPersonId());
        validateTime(slotEntity);
        return this.slotRepository.save(slotEntity);
    }

    private void validatePerson(final Long personId) {
        try {
            this.personService.findPersonById(personId);
        } catch (final PersonNotFoundException ex) {
            throw new SlotInvalidPersonException();
        }
    }

    private void validateTime(final SlotEntity slotEntity) {
        this.slotRepository.findSlotByPersonIdAndSlotDateAndSlotHour(slotEntity.getPersonId(), slotEntity.getSlotDate(), slotEntity.getSlotHour())
                .ifPresent(person -> {
                    throw new SlotInvalidException();
                });
    }

    public List<SlotEntity> findMatchedSlots(final SlotFilterDTO slotFilterDTO) {
        final List<SlotEntity> matchedSlots = this.slotRepository.findMatchedSlots(slotFilterDTO.getCandidateId(), slotFilterDTO.getInterviewersID());
        if (CollectionUtils.isEmpty(matchedSlots)) {
            throw new SlotMatchedNotFoundException();
        }
        return matchedSlots;
    }

}
