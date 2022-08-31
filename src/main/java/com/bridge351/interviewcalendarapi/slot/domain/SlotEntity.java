package com.bridge351.interviewcalendarapi.slot.domain;

import com.bridge351.interviewcalendarapi.user.domain.UserEntity;
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
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

    @Column(name = "SLOT_DATE")
    private LocalDate slotDate;

    @Column(name = "SLOT_HOUR")
    private int slotHour;

    public static SlotEntity ofSlotWithDateAndTime(final Long userId, final SlotRequestDateTimeDTO slotRequestDateTime) {
        final UserEntity user = UserEntity.builder()
                .id(userId)
                .build();
        return SlotEntity.builder()
                .user(user)
                .slotDate(slotRequestDateTime.getSlotDate())
                .slotHour(slotRequestDateTime.getSlotHour())
                .build();
    }

}
