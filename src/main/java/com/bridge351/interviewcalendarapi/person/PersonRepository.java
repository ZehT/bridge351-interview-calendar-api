package com.bridge351.interviewcalendarapi.person;

import com.bridge351.interviewcalendarapi.person.domain.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    List<PersonEntity> findAllPersonsByType(final int type);

    Optional<PersonEntity> findPersonByEmail(final String email);

}
