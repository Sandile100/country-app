package com.om.countryapp.model;

public record CountryDetails(
        String name,
        Integer population,
        String capital,
        String flag
) {
}
