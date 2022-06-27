package com.himalaya.demo.himalaya.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;

@AllArgsConstructor
@Getter
public enum DeveloperLevel {

//    JUNIOR("junior developer", 0, 4),
//    JUNGNIOR("jungnior developer", 5, 12),
//    SENIOR("senior developer", 13, 50);
//
//    private final String description;
//    private final int minExperienceYear;
//    private final int maxExperienceYear;

    JUNIOR("junior developer", years -> years <= 4),
    JUNGNIOR("jungnior developer", years -> years >= 5 && years <= 12),
    SENIOR("senior developer", years -> years >= 13);

    private final String description;
    private final Function<Integer, Boolean> validateFunction;

    public void validateDeveloperLevel(Integer experienceYear) {
        if (!validateFunction.apply(experienceYear)) {
            throw new IllegalArgumentException();
        }
    }

}
