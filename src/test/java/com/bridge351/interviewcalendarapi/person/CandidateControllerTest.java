package com.bridge351.interviewcalendarapi.person;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.person.api.candidate.CandidateController;
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

/**
 * <p>Class that represents some Behavior Tests mocking everything with Mockito always expecting desired results.</p>
 */
@RunWith(MockitoJUnitRunner.class)
public class CandidateControllerTest {

    @InjectMocks
    private CandidateController candidateController;

    @Mock
    private PersonService personService;

    @Mock
    private MessageSource messageSource;

    @Test
    public void whenGetCandidatesThenValidateResponse() {
        final int candidateType = 1;
        final List<PersonEntity> personsExpected = new ArrayList<>();
        final PersonEntity person = PersonEntity.builder()
                .name("trein")
                .build();
        personsExpected.add(person);
        when(this.personService.findAllPersonsByType(candidateType)).thenReturn(personsExpected);
        final BasicResponse<List<PersonDTO>> response = this.candidateController.getCandidates();
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
        final PersonEntity ofDto = PersonEntity.ofDto(personRequest, PersonTypeEnum.CANDIDATE.getType());
        final PersonEntity expectedPerson = PersonEntity.builder()
                .id(1L)
                .build();
        final String expectedMsg = "Candidate created";
        when(this.personService.addPerson(ofDto)).thenReturn(expectedPerson);
        when(this.messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn(expectedMsg);
        final BasicResponse<PersonDTO> response = this.candidateController.addCandidate(personRequest);
        verify(this.personService).addPerson(ofDto);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(expectedMsg, response.getMessage());
        assertEquals(expectedPerson.getId(), response.getData().getId());
    }

}
