package com.bridge351.interviewcalendarapi.slot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Builder
@Table(name = "SLOT")
@NoArgsConstructor
@AllArgsConstructor
public class SlotEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PERSON_ID")
    private Long personId;

    @Column(name = "START_AT")
    private LocalDateTime startAt;

    public static SlotEntity ofDTO(final SlotDTO slotDTO) {
        final LocalTime time = LocalTime.of(slotDTO.getSlotStartTime(), 0, 0);
        final LocalDateTime startAt = LocalDateTime.of(slotDTO.getSlotDate(), time);
        return SlotEntity.builder()
                .personId(slotDTO.getPersonId())
                .startAt(startAt)
                .build();
    }

}
