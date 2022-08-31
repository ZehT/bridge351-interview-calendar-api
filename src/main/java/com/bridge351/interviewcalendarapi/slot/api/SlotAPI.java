package com.bridge351.interviewcalendarapi.slot.api;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.slot.domain.SlotDTO;
import com.bridge351.interviewcalendarapi.slot.domain.SlotFilterDTO;
import com.bridge351.interviewcalendarapi.slot.domain.SlotRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Slot API", tags = "Slot API")
public interface SlotAPI {

    @ApiOperation(value = "Find Slots for a Person", nickname = "findSlotsByPerson", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SlotDTO.class, responseContainer = "list"),
            @ApiResponse(code = 200, message = "No Matched Slots Found"),
            @ApiResponse(code = 422, message = "Unable to query API"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @GetMapping(value = "/slots/person/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<List<SlotDTO>> findSlotsByPerson(@ApiParam(value = "Person Identifier", required = true) @RequestParam final Long personId);

    @ApiOperation(value = "Find Matched Slots Between Candidates and Interviewers", nickname = "findMatchedSlots", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SlotDTO.class, responseContainer = "list"),
            @ApiResponse(code = 200, message = "No Matched Slots Found"),
            @ApiResponse(code = 422, message = "Unable to query API"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @GetMapping(value = "/slots/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<List<SlotDTO>> findMatchedSlots(@ApiParam(value = "Filters", required = true)
                                                  @Valid @ModelAttribute final SlotFilterDTO slotFilter);

    @ApiOperation(value = "Add Slots", nickname = "addSlots", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Slot(s) added"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unable to add Slot"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @PostMapping(value = "/slots/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<Void> addSlots(@ApiParam(value = "Slots to add") @Valid @RequestBody final SlotRequestDTO slotRequest);

}
