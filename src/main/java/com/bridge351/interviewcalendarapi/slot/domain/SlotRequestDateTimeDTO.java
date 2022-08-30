package com.bridge351.interviewcalendarapi.slot.domain;

import com.bridge351.interviewcalendarapi.commons.serializer.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlotRequestDateTimeDTO {

    @ApiModelProperty(value = "Slot Date", example = "08/30/2022", required = true, position = 2)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "commons.validation.mandatory.field")
    private LocalDate slotDate;

    @ApiModelProperty(value = "Slot Start Hour 24H format", example = "13", required = true, position = 3)
    @NotNull(message = "commons.validation.mandatory.field")
    @Min(value = 0, message = "commons.validation.minvalue.zero")
    @Max(value = 23, message = "commons.validation.maxvalue.twenty-three")
    private int slotHour;

}
