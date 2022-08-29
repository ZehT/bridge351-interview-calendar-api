package com.bridge351.interviewcalendarapi.person.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Class that represents an Interviewer or a Candidate.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Long id;
    private String name;
    private String email;

    public static PersonDTO ofEntity(final PersonEntity personEntity) {
        return PersonDTO.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .email(personEntity.getEmail())
                .build();
    }

}
