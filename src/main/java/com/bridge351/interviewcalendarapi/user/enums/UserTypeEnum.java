package com.bridge351.interviewcalendarapi.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

/**
 * <p>Enum to Represent the User's Type.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserTypeEnum {

    CANDIDATE(1),
    INTERVIEWER(2);

    private int type;

    public static UserTypeEnum getByType(final int type) {
        return Stream.of(UserTypeEnum.values())
                .filter(enumType -> enumType.getType() == type)
                .findFirst()
                .orElse(null);
    }
}
