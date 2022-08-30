package com.bridge351.interviewcalendarapi.slot;

import com.bridge351.interviewcalendarapi.slot.domain.SlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<SlotEntity, Long> {

    Optional<SlotEntity> findSlotByPersonIdAndStartAt(final Long personId, final LocalDateTime startAt);

    @Query(value = ""
            + "SELECT "
            + "  DISTINCT INTERVIEWER_SLOT.* "
            + "FROM "
            + "  SLOT AS CANDIDATE_SLOT "
            + "  INNER JOIN SLOT AS INTERVIEWER_SLOT ON "
            + "    EXTRACT(HOUR FROM INTERVIEWER_SLOT.START_AT ) "
            + "    = "
            + "    EXTRACT(HOUR FROM CANDIDATE_SLOT.START_AT) "
            + "  AND INTERVIEWER_SLOT.PERSON_ID IN (:interviewersID) "
            + "WHERE "
            + "  CANDIDATE_SLOT.PERSON_ID = :candidateId "
            + "ORDER BY "
            + "  INTERVIEWER_SLOT.PERSON_ID",
            nativeQuery = true)
    List<SlotEntity> findMatchedSlots(@Param("candidateId") final Long candidateId, @Param("interviewersID") final List<Long> interviewersID);

}
