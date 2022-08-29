package com.bridge351.interviewcalendarapi.person.api.interviewer;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.person.domain.PersonDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "Interviewer API", tags = "Interviewer API")
public interface InterviewerAPI {

    @ApiOperation(value = "Add Interviewer", nickname = "addInterviewer", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Interviewer created", response = PersonDTO.class),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @PostMapping(value = "/interviewers/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<PersonDTO> addInterviewer(@ApiParam(value = "Interviewer to create") @RequestBody final PersonDTO personDTO);

}
