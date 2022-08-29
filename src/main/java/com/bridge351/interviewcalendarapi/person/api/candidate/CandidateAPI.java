package com.bridge351.interviewcalendarapi.person.api.candidate;

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

@Api(value = "Candidate API", tags = "Candidate API")
public interface CandidateAPI {

    @ApiOperation(value = "Add Candidate", nickname = "addCandidate", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Candidate created.", response = PersonDTO.class),
            @ApiResponse(code = 500, message = "Server Error.")
    })
    @PostMapping(value = "/candidates/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<PersonDTO> addCandidate(@ApiParam(value = "Candidate to create") @RequestBody final PersonDTO personDTO);

}