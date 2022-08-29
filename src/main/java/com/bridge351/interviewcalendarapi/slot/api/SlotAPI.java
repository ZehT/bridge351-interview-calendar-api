package com.bridge351.interviewcalendarapi.slot.api;

import com.bridge351.interviewcalendarapi.config.BasicResponse;
import com.bridge351.interviewcalendarapi.slot.domain.SlotDTO;
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

@Api(value = "Slot API", tags = "Slot API")
public interface SlotAPI {

    @ApiOperation(value = "Find Available Slots", nickname = "findSlots", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SlotDTO.class, responseContainer = "list"),
            @ApiResponse(code = 200, message = "No Slots Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @GetMapping(value = "/slots/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<List<SlotDTO>> findSlots();

    @ApiOperation(value = "Add Slot", nickname = "addSlot", response = BasicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Slot added.", response = SlotDTO.class),
            @ApiResponse(code = 500, message = "Server Error.")
    })
    @PostMapping(value = "/slots/", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<SlotDTO> addSlot(@ApiParam(value = "Slot to add") @RequestBody final SlotDTO slotDTO);

}
