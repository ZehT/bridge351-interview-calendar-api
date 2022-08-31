package com.bridge351.interviewcalendarapi.slot;

import com.bridge351.interviewcalendarapi.slot.domain.SlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<SlotEntity, Long> {

    List<SlotEntity> findSlotByUserId(final Long userId);

    Optional<SlotEntity> findSlotByUserIdAndSlotDateAndSlotHour(final Long userId,
                                                                final LocalDate slotDate,
                                                                final int slotHour);

    /**
     * <p>Native Query to find matched slots between candidate and interviewers.</p>
     * <p>Aside from validating the Slot Date and Hour the query also makes sure that the candidate is from a candidate
     * and the interviewers are from interviewers.</p>
     *
     * @param candidateId    candidate id
     * @param interviewersID list of interviewers id
     * @return matched slots
     */
    @Query(value = ""
            + "SELECT "
            + "  DISTINCT interviewer_slot.* "
            + "FROM "
            + "  SLOT AS candidate_slot "
            + "  INNER JOIN USER candidate ON "
            + "      candidate.ID = candidate_slot.USER_ID "
            + "      AND candidate.TYPE = 1 "
            + "  INNER JOIN SLOT AS interviewer_slot ON "
            + "      interviewer_slot.SLOT_DATE = candidate_slot.SLOT_DATE "
            + "      AND interviewer_slot.SLOT_HOUR = candidate_slot.SLOT_HOUR "
            + "      AND interviewer_slot.USER_ID IN (:interviewersID) "
            + "  INNER JOIN USER interviewer ON "
            + "      interviewer.ID = interviewer_slot.USER_ID "
            + "      AND interviewer.TYPE = 2 "
            + "WHERE "
            + "  candidate_slot.USER_ID = :candidateId "
            + "ORDER BY "
            + "  interviewer_slot.USER_ID",
            nativeQuery = true)
    List<SlotEntity> findMatchedSlots(@Param("candidateId") final Long candidateId,
                                      @Param("interviewersID") final List<Long> interviewersID);
}
