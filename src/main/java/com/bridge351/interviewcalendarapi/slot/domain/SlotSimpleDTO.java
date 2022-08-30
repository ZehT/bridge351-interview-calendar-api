package com.bridge351.interviewcalendarapi.slot.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>Class that represents a Slot of a Person.</p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SlotSimpleDTO extends SlotDTO {

    @ApiModelProperty(value = "Slot ID - ignored on post", example = "1")
    private Long id;

    public static SlotSimpleDTO ofEntity(final SlotEntity slotEntity) {
        return SlotSimpleDTO.builder()
                .id(slotEntity.getId())
                .personId(slotEntity.getPersonId())
                .slotDate(slotEntity.getSlotDate())
                .slotHour(slotEntity.getSlotHour())
                .build();
    }

}
