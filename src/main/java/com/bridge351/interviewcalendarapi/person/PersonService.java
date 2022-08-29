package com.bridge351.interviewcalendarapi.person;

import com.bridge351.interviewcalendarapi.person.domain.PersonEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
