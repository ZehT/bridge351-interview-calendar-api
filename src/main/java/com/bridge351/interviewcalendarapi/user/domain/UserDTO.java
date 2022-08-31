package com.bridge351.interviewcalendarapi.user.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * <p>Class that represents an Interviewer or a Candidate that already exists in the DB.</p>
 * <p>This class extends UserRequestDTO to avoid attribute duplication.</p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends UserRequestDTO {

    @ApiModelProperty(value = "Candidate Or Interviewer ID", example = "1")
    private Long id;

    public static UserDTO ofEntity(final UserEntity user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
