package com.himalaya.demo.himalaya.service;

import com.himalaya.demo.himalaya.dto.CreateDeveloper;
import com.himalaya.demo.himalaya.entity.DeveloperLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;

class DeveloperServiceTest {

    DeveloperService developerService = new DeveloperService();

    @Test
    @DisplayName("주니어개발자레벨 유효성 실패 테스트")
    void validateJuniorDeveloperLevelTest_fail() throws Exception {
        //given
        //when
        //then
        thenThrownBy(() -> {
            developerService.validateDeveloperLevel(getDefaultDeveloper(DeveloperLevel.JUNIOR, 5));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중니어개발자레벨 유효성 실패 테스트")
    void validateJungniorDeveloperLevelTest_fail() throws Exception {
        //given
        //when
        //then
        thenThrownBy(() -> {
            developerService.validateDeveloperLevel(getDefaultDeveloper(DeveloperLevel.JUNGNIOR, 4));
        }).isInstanceOf(IllegalArgumentException.class);

        thenThrownBy(() -> {
            developerService.validateDeveloperLevel(getDefaultDeveloper(DeveloperLevel.JUNGNIOR, 13));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("시니어개발자레벨 유효성 실패 테스트")
    void validateSeniorDeveloperLevelTest_fail() throws Exception {
        //given
        //when
        //then
        thenThrownBy(() -> {
            developerService.validateDeveloperLevel(getDefaultDeveloper(DeveloperLevel.SENIOR, 12));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    private CreateDeveloper.Request getDefaultDeveloper(final DeveloperLevel developerLevel, final Integer experienceYear) {
        return CreateDeveloper.Request.builder()
                .name("tester")
                .developerLevel(developerLevel)
                .experienceYear(experienceYear)
                .build();
    }
}