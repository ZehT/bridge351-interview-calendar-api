package com.bridge351.interviewcalendarapi.person.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>Class that represents an Interviewer or a Candidate that already exists in the DB.</p>
 * <p>This class extends PersonRequestDTO to avoid attribute duplication.</p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PersonDTO extends PersonRequestDTO {

    @ApiModelProperty(value = "Candidate Or Interviewer ID", example = "1")
    private Long id;

    public static PersonDTO ofEntity(final PersonEntity person) {
        return PersonDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .email(person.getEmail())
                .build();
    }

}
