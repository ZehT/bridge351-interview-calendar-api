package com.bridge351.interviewcalendarapi.person;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.person.api.interviewer.InterviewerController;
import com.bridge351.interviewcalendarapi.person.domain.PersonDTO;
import com.bridge351.interviewcalendarapi.person.domain.PersonEntity;
import com.bridge351.interviewcalendarapi.person.domain.PersonRequestDTO;
import com.bridge351.interviewcalendarapi.person.enums.PersonTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InterviewerControllerTest {

    @InjectMocks
    private InterviewerController interviewerController;

    @Mock
    private PersonService personService;

    @Mock
    private MessageSource messageSource;

    @Test
    public void whenGetCandidatesThenValidateResponse() {
        final int candidateType = 2;
        final List<PersonEntity> personsExpected = new ArrayList<>();
        final PersonEntity person = PersonEntity.builder()
                .name("trein")
                .build();
        personsExpected.add(person);
        when(this.personService.findAllPersonsByType(candidateType)).thenReturn(personsExpected);
        final BasicResponse<List<PersonDTO>> response = this.interviewerController.getInterviewers();
        verify(this.personService).findAllPersonsByType(candidateType);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(person.getName(), response.getData().get(0).getName());
    }

    @Test
    public void givenPersonRequestWhenAddCandidatesThenValidateResponse() {
        final PersonRequestDTO personRequest = PersonRequestDTO.builder()
                .name("trein")
                .email("trein@mail.com")
                .build();
        final PersonEntity ofDto = PersonEntity.ofDto(personRequest, PersonTypeEnum.INTERVIEWER.getType());
        final PersonEntity expectedPerson = PersonEntity.builder()
                .id(1L)
                .build();
        final String expectedMsg = "Candidate created";
        when(this.personService.addPerson(ofDto)).thenReturn(expectedPerson);
        when(this.messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn(expectedMsg);
        final BasicResponse<PersonDTO> response = this.interviewerController.addInterviewer(personRequest);
        verify(this.personService).addPerson(ofDto);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(expectedMsg, response.getMessage());
        assertEquals(expectedPerson.getId(), response.getData().getId());
    }

}
