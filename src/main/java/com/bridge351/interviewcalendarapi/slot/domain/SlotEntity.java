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
import java.time.LocalDate;

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

    @Column(name = "SLOT_DATE")
    private LocalDate slotDate;

    @Column(name = "SLOT_HOUR")
    private int slotHour;

    public static SlotEntity ofDTO(final SlotDTO slotDTO) {
        return SlotEntity.builder()
                .personId(slotDTO.getPersonId())
                .slotDate(slotDTO.getSlotDate())
                .slotHour(slotDTO.getSlotHour())
                .build();
    }

}
