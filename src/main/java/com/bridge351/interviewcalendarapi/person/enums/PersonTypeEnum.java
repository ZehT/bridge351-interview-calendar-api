package com.bridge351.interviewcalendarapi.person.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>Enum to Represent the Person's Type.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PersonTypeEnum {

    CANDIDATE(1),
    INTERVIEWER(2);

    private int id;

}
