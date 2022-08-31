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

import java.time.LocalDate;

/**
 * <p>Class that represents a Slot of a Person.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlotDTO {

    @ApiModelProperty(value = "Slot ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "Candidate or Interview name", example = "Trein", position = 1)
    private String name;

    @ApiModelProperty(value = "Slot Date", example = "08/30/2022", position = 2)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate slotDate;

    @ApiModelProperty(value = "Slot Start Hour 24H format", example = "13", position = 3)
    private int slotHour;

    public static SlotDTO ofEntity(final SlotEntity slotEntity) {
        return SlotDTO.builder()
                .id(slotEntity.getId())
                .name(slotEntity.getPerson().getName())
                .slotDate(slotEntity.getSlotDate())
                .slotHour(slotEntity.getSlotHour())
                .build();
    }

}
