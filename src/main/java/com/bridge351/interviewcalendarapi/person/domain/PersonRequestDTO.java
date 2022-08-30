package com.bridge351.interviewcalendarapi.person.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>Class that represents an Interviewer or a Candidate to be created.</p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDTO {

    @ApiModelProperty(value = "Candidate Or Interviewer name", example = "Trein", required = true, position = 1)
    @NotBlank(message = "commons.validation.mandatory.field")
    @Size(max = 50, message = "commons.validation.maxsize.fifty")
    private String name;

    @ApiModelProperty(value = "Candidate Or Interviewer name", example = "myEmail@gmail.com", required = true, position = 2)
    @NotBlank(message = "commons.validation.mandatory.field")
    @Email(message = "commons.validation.email.format")
    @Size(max = 50, message = "commons.validation.maxsize.fifty")
    private String email;

}
