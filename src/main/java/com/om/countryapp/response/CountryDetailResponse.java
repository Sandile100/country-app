package com.om.countryapp.response;

import java.util.List;

public record CountryDetailResponse(
        Flag flags,
        Name name,
        Integer population,
        List<String> capital
) {
}
