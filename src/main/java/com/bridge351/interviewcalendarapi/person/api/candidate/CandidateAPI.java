package com.bridge351.interviewcalendarapi.person.api.candidate;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.person.domain.PersonRequestDTO;
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

import javax.validation.Valid;
import java.util.List;

@Api(value = "Candidate API", tags = "Candidate API")
public interface CandidateAPI {

    @ApiOperation(value = "List of Candidates", nickname = "getCandidates", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "", response = PersonDTO.class, responseContainer = "list"),
            @ApiResponse(code = 200, message = "No Candidates Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @GetMapping(value = "/candidates/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<List<PersonDTO>> getCandidates();

    @ApiOperation(value = "Add Candidate", nickname = "addCandidate", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Candidate created", response = PersonDTO.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unable to add Candidate"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @PostMapping(value = "/candidates/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<PersonDTO> addCandidate(@ApiParam(value = "Candidate to create") @Valid @RequestBody final PersonRequestDTO personRequest);

}
