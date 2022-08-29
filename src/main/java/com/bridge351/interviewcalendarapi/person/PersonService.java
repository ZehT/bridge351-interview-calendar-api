package com.bridge351.interviewcalendarapi.person;

import com.bridge351.interviewcalendarapi.person.domain.PersonEntity;
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
        return this.personRepository.save(personEntity);
    }

    public List<PersonEntity> findAllPersonsByType(final int typeId) {
        final List<PersonEntity> personsEntity = this.personRepository.findAllPersonsByType(typeId);
        if (CollectionUtils.isEmpty(personsEntity)) {
            throw new PersonNotFoundException();
        }
        return personsEntity;
    }

}
