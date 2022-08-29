package com.bridge351.interviewcalendarapi.person.api.candidate;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.person.PersonService;
import com.bridge351.interviewcalendarapi.person.domain.PersonDTO;
import com.bridge351.interviewcalendarapi.person.domain.PersonEntity;
import com.bridge351.interviewcalendarapi.person.enums.PersonTypeEnum;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateController implements CandidateAPI {

    final PersonService personService;

    public CandidateController(final PersonService personService) {
        this.personService = personService;
    }

    @Override
    public BasicResponse<PersonDTO> addCandidate(final PersonDTO personDTO) {
        final PersonEntity personEntity = this.personService.addPerson(PersonEntity.ofDTO(personDTO, PersonTypeEnum.CANDIDATE.getId()));
        return BasicResponse.withDataAndMessage(
                PersonDTO.ofEntity(personEntity),
                "OK"
        );
    }

}
