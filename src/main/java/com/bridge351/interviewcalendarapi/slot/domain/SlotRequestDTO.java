package com.bridge351.interviewcalendarapi.slot.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>Class that represents a Slot of a User to be created.</p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SlotRequestDTO {

    @ApiModelProperty(value = "Candidate Or Interviewer ID", example = "1", required = true, position = 1)
    @NotNull(message = "commons.validation.mandatory.field")
    private Long userId;

    @ApiModelProperty(value = "Candidate Or Interviewer List of Slots", required = true, position = 1)
    @NotNull(message = "commons.validation.mandatory.field")
    private List<SlotRequestDateTimeDTO> slotDateTimeList;

}
