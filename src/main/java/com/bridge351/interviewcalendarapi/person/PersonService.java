package com.bridge351.interviewcalendarapi.person;

import com.bridge351.interviewcalendarapi.person.domain.PersonEntity;
import com.bridge351.interviewcalendarapi.person.enums.PersonTypeEnum;
import com.bridge351.interviewcalendarapi.person.exception.PersonAlreadyExistsException;
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
    public PersonEntity addPerson(final PersonEntity person) {
        this.personRepository.findPersonByEmail(person.getEmail())
                .ifPresent(found -> {
                    throw new PersonAlreadyExistsException();
                });
        return this.personRepository.save(person);
    }

    public List<PersonEntity> findAllPersonsByType(final int type) {
        final List<PersonEntity> persons = this.personRepository.findAllPersonsByType(type);
        validatePersonsBeenFound(persons, type);
        return persons;
    }

    private void validatePersonsBeenFound(final List<PersonEntity> persons, final int type) {
        if (CollectionUtils.isEmpty(persons)) {
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
