package com.bridge351.interviewcalendarapi.slot.domain;

import com.bridge351.interviewcalendarapi.person.domain.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity person;

    @Column(name = "SLOT_DATE")
    private LocalDate slotDate;

    @Column(name = "SLOT_HOUR")
    private int slotHour;

    public static SlotEntity ofSlotWithDateAndTime(final Long personId, final SlotRequestDateTimeDTO slotRequestDateTime) {
        final PersonEntity person = PersonEntity.builder()
                .id(personId)
                .build();
        return SlotEntity.builder()
                .person(person)
                .slotDate(slotRequestDateTime.getSlotDate())
                .slotHour(slotRequestDateTime.getSlotHour())
                .build();
    }

}
