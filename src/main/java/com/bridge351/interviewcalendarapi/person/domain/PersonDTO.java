package com.bridge351.interviewcalendarapi.person.domain;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "Candidate Or Interviewer ID - ignored on post", example = "1")
    private Long id;
    @ApiModelProperty(value = "Candidate Or Interviewer name", example = "Trein", required = true)
    private String name;
    @ApiModelProperty(value = "Candidate Or Interviewer name", example = "myEmail@gmail.com", required = true)
    private String email;

    public static PersonDTO ofEntity(final PersonEntity personEntity) {
        return PersonDTO.builder()
                .id(personEntity.getId())
                .name(personEntity.getName())
                .email(personEntity.getEmail())
                .build();
    }

}
