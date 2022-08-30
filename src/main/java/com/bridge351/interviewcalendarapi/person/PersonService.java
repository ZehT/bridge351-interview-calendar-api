package com.bridge351.interviewcalendarapi.person;

import com.bridge351.interviewcalendarapi.commons.exceptions.BusinessException;
import com.bridge351.interviewcalendarapi.person.domain.PersonEntity;
import com.bridge351.interviewcalendarapi.person.enums.PersonTypeEnum;
import com.bridge351.interviewcalendarapi.person.exception.PersonNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public PersonEntity addPerson(final PersonEntity personEntity) {
        this.personRepository.findPersonByEmail(personEntity.getEmail())
                .ifPresent(person -> {
                    throw new BusinessException("person.exception.person.already.exists");
                });
        return this.personRepository.save(personEntity);
    }

    public List<PersonEntity> findAllPersonsByType(final int type) {
        final List<PersonEntity> personsEntity = this.personRepository.findAllPersonsByType(type);
        validatePersonsBeenFound(personsEntity, type);
        return personsEntity;
    }

    private void validatePersonsBeenFound(final List<PersonEntity> personsEntity, final int type) {
        if (CollectionUtils.isEmpty(personsEntity)) {
            String notFoundMsg = "person.exception.candidate.not.found";
            if (PersonTypeEnum.INTERVIEWER.getType() == type) {
                notFoundMsg = "person.exception.interviewer.not.found";
            }
            throw new PersonNotFoundException(notFoundMsg);
        }
    }

    public void findPersonById(final Long id) throws PersonNotFoundException {
        this.personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

}
