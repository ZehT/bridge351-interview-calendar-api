package com.bridge351.interviewcalendarapi.person.api.interviewer;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.person.domain.PersonDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterviewerController implements InterviewerAPI {

    @Override
    public BasicResponse<PersonDTO> addInterviewer(final PersonDTO personDTO) {
        return null;
    }

}
