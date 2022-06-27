package com.himalaya.demo.himalaya.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Developer {

    private String memberId;
    private String name;
    private DeveloperLevel developerLevel;
    private Integer experienceYear;

}
