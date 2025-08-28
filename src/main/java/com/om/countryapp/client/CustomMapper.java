package com.om.countryapp.client;

import com.om.countryapp.model.Country;
import com.om.countryapp.model.CountryDetails;
import com.om.countryapp.response.CountryDetailResponse;
import com.om.countryapp.response.CountryResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomMapper {

    public Country countryToCountryResponse(CountryResponse countryResponse) {
        return  new Country(
                countryResponse.name().common(),
                countryResponse.flags().svg()
        );
    }

    public CountryDetails countryDetailsToCountryDetailResponse(CountryDetailResponse countryDetailResponse) {
        return new CountryDetails(
                countryDetailResponse.name().common(),
                countryDetailResponse.population(),
                countryDetailResponse.capital().isEmpty() ? null : countryDetailResponse.capital().get(0),
                countryDetailResponse.flags().svg()
        );
    }
}
