package com.bridge351.interviewcalendarapi.slot;

import com.bridge351.interviewcalendarapi.person.PersonService;
import com.bridge351.interviewcalendarapi.person.exception.PersonNotFoundException;
import com.bridge351.interviewcalendarapi.slot.domain.SlotEntity;
import com.bridge351.interviewcalendarapi.slot.exception.SlotException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return this.slotRepository.save(slotEntity);
    }

    private void validatePerson(final Long personId) {
        try {
            this.personService.findPersonById(personId);
        } catch (final PersonNotFoundException ex) {
            throw new SlotException("slot.exception.invalid.person");
        }
    }

}
