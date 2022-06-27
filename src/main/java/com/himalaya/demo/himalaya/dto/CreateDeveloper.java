package com.himalaya.demo.himalaya.dto;

import com.himalaya.demo.himalaya.entity.DeveloperLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Builder
public class CreateDeveloper {


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        @NotNull
        private String name;

        @NotNull
        private DeveloperLevel developerLevel;

        @NotNull
        private Integer experienceYear;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private String memberId;
        private String name;
        private DeveloperLevel developerLevel;
        private Integer experienceYear;
    }
}
