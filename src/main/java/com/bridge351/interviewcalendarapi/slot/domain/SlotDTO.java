package com.bridge351.interviewcalendarapi.slot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>Class that represents a Slot of a Person.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlotDTO {

    private Long personId;
    private LocalDateTime slotDate;
    private int slotStartTime;

}
