package com.bridge351.interviewcalendarapi.person.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@Table(name = "PERSON")
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TYPE")
    private int type;

    public static PersonEntity ofDto(final PersonRequestDTO personRequest, final int type) {
        return PersonEntity.builder()
                .name(personRequest.getName())
                .email(personRequest.getEmail())
                .type(type)
                .build();
    }

}
