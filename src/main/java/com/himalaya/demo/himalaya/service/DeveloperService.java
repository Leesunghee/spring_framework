package com.himalaya.demo.himalaya.service;


import com.himalaya.demo.himalaya.constant.DeveloperLevelYear;
import com.himalaya.demo.himalaya.dto.CreateDeveloper;
import com.himalaya.demo.himalaya.entity.DeveloperLevel;
import org.springframework.stereotype.Service;

import static com.himalaya.demo.himalaya.constant.DeveloperLevelYear.*;
import static com.himalaya.demo.himalaya.constant.DeveloperLevelYear.MIN_SENIOR_EXPERIENCE_YEAR;

@Service
public class DeveloperService {


    public void validateDeveloperLevel(CreateDeveloper.Request request) {

//        if (request.getDeveloperLevel().name().equals("JUNIOR")
//                && request.getExperienceYear() > MAX_JUNIOR_EXPERIENCE_YEAR) {
//            throw new IllegalArgumentException();
//        }
//        if (request.getDeveloperLevel().name().equals("JUNGNIOR")
//                && (request.getExperienceYear() <= MAX_JUNIOR_EXPERIENCE_YEAR
//                || request.getExperienceYear() > MIN_SENIOR_EXPERIENCE_YEAR)) {
//            throw new IllegalArgumentException();
//        }
//        if (request.getDeveloperLevel().name().equals("SENIOR")
//                && request.getExperienceYear() <= MIN_SENIOR_EXPERIENCE_YEAR) {
//            throw new IllegalArgumentException();
//        }


//        if (request.getDeveloperLevel().getMinExperienceYear() > request.getExperienceYear()
//                || request.getDeveloperLevel().getMaxExperienceYear() < request.getExperienceYear()) {
//            throw new IllegalArgumentException();
//        }

        request.getDeveloperLevel().validateDeveloperLevel(request.getExperienceYear());
    }

}
