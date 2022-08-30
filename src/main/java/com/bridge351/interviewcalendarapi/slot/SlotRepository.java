package com.bridge351.interviewcalendarapi.slot;

import com.bridge351.interviewcalendarapi.slot.domain.SlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<SlotEntity, Long> {

}
