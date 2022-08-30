package com.bridge351.interviewcalendarapi.person.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>Class that represents an Interviewer or a Candidate that already exists in the DB, extending the PersonSimpleDTO
 * to avoid attribute duplication and so it can have access to all is attributes.</p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PersonSimpleDTO extends PersonDTO {

    @ApiModelProperty(value = "Candidate Or Interviewer ID", example = "1")
    private Long id;

    public static PersonSimpleDTO ofEntity(final PersonEntity personEntity) {
        return PersonSimpleDTO.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .email(personEntity.getEmail())
                .build();
    }

}
