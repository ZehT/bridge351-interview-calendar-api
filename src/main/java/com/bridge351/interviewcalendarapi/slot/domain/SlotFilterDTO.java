package com.bridge351.interviewcalendarapi.slot.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>Class that represents the Filters to Query for matched Slots.</p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SlotFilterDTO {

    @ApiModelProperty(value = "Candidate ID", example = "1", required = true)
    @NotNull(message = "commons.validation.mandatory.field")
    private Long candidateId;

    @ApiModelProperty(value = "Interviewers ID", example = "[1, 2]", required = true)
    @NotNull(message = "commons.validation.mandatory.field")
    private List<Long> interviewersId;

}
