package com.bridge351.interviewcalendarapi.person.api.candidate;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.person.domain.PersonDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateController implements CandidateAPI {

    @Override
    public BasicResponse<PersonDTO> addCandidate(final PersonDTO personDTO) {
        return null;
    }

}
