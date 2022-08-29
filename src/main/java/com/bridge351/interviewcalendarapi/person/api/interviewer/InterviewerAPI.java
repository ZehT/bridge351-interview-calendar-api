package com.bridge351.interviewcalendarapi.person.api.interviewer;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.person.domain.PersonDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(value = "Interviewer API", tags = "Interviewer API")
public interface InterviewerAPI {

    @ApiOperation(value = "List of Interviewers", nickname = "getInterviewers", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = PersonDTO.class, responseContainer = "list"),
            @ApiResponse(code = 200, message = "No Candidates Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @GetMapping(value = "/interviewers/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<List<PersonDTO>> getInterviewers();

    @ApiOperation(value = "Add Interviewer", nickname = "addInterviewer", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Interviewer created", response = PersonDTO.class),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @PostMapping(value = "/interviewers/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<PersonDTO> addInterviewer(@ApiParam(value = "Interviewer to create") @RequestBody final PersonDTO personDTO);

}
