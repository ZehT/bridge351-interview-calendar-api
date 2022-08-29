package com.bridge351.interviewcalendarapi.person.api.interviewer;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.person.PersonService;
import com.bridge351.interviewcalendarapi.person.domain.PersonDTO;
import com.bridge351.interviewcalendarapi.person.domain.PersonEntity;
import com.bridge351.interviewcalendarapi.person.enums.PersonTypeEnum;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class InterviewerController implements InterviewerAPI {

    private final PersonService personService;
    private final MessageSource messageSource;

    public InterviewerController(final PersonService personService, final MessageSource messageSource) {
        this.personService = personService;
        this.messageSource = messageSource;
    }

    @Override
    public BasicResponse<List<PersonDTO>> getInterviewers() {
        final List<PersonEntity> personsEntity = this.personService.findAllPersonsByType(PersonTypeEnum.INTERVIEWER.getType());
        return BasicResponse.withData(personsEntity.stream()
                .map(PersonDTO::ofEntity)
                .collect(Collectors.toList()));
    }

    @Override
    public BasicResponse<PersonDTO> addInterviewer(final PersonDTO personDTO) {
        final PersonEntity personEntity = this.personService.addPerson(PersonEntity.ofDTO(personDTO, PersonTypeEnum.INTERVIEWER.getType()));
        return BasicResponse.withDataAndMessage(
                PersonDTO.ofEntity(personEntity),
                this.messageSource.getMessage("person.interviewer.created", null, Locale.getDefault())
        );
    }

}
