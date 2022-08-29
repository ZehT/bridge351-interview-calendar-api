package com.bridge351.interviewcalendarapi.person;

import com.bridge351.interviewcalendarapi.person.domain.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

}
