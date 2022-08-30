package com.bridge351.interviewcalendarapi.slot.domain;

import com.bridge351.interviewcalendarapi.commons.serializer.LocalDateDeserializer;
import com.bridge351.interviewcalendarapi.commons.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * <p>Class that represents a Slot of a Person.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlotDTO {

    @ApiModelProperty(value = "Slot ID - ignored on post", example = "1")
    private Long id;
    @ApiModelProperty(value = "Candidate Or Interviewer ID", example = "1", required = true)
    @NotNull(message = "commons.validation.mandatory.field")
    private Long personId;
    @ApiModelProperty(value = "Slot Date", example = "08/30/2022", required = true)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "commons.validation.mandatory.field")
    private LocalDate slotDate;
    @ApiModelProperty(value = "Slot Start Time - goes from 0 to 23", example = "13", required = true)
    @NotNull(message = "commons.validation.mandatory.field")
    @Min(value = 0, message = "commons.validation.minvalue.zero")
    @Max(value = 23, message = "commons.validation.maxvalue.twenty-three")
    private int slotStartTime;

    public static SlotDTO ofEntity(final SlotEntity slotEntity) {
        return SlotDTO.builder()
                .id(slotEntity.getId())
                .personId(slotEntity.getPersonId())
                .slotDate(slotEntity.getStartAt().toLocalDate())
                .slotStartTime(slotEntity.getStartAt().getHour())
                .build();
    }

}
