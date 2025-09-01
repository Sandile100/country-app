package com.om.countryapp.service;

import com.om.countryapp.client.CustomMapper;
import com.om.countryapp.model.Country;
import com.om.countryapp.model.CountryDetails;
import com.om.countryapp.response.CountryDetailResponse;
import com.om.countryapp.response.CountryResponse;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    private final WebClient webClient;
    private final CustomMapper mapper;
    private final CacheManager myCacheManager;

    public CountryService(WebClient webClient, CustomMapper mapper, CacheManager myCacheManager) {
        this.webClient = webClient;
        this.mapper = mapper;
        this.myCacheManager = myCacheManager;
    }

    @Cacheable(value = "CountryCache", cacheManager = "myCacheManager", unless="#result == null")
    public List<Country> getAllCountries() {

        List<Country> countries = new ArrayList<>();
        List<CountryResponse> countryResponses = webClient.get()
                .uri("?fields=name,flags")
                .retrieve()
                .bodyToFlux(CountryResponse.class)
                .collectList()
                .block();

        assert countryResponses != null;
        for (CountryResponse countryResponse : countryResponses) {
            countries.add(mapper.countryToCountryResponse(countryResponse));
        }
        return countries;
    }

    @Cacheable(value = "CountryDetailsCache", cacheManager = "myCacheManager", unless="#result == null")
    public CountryDetails getCountryDetails(final String countryName) {
        CountryDetails countryDetails;
        List<CountryDetailResponse> countryDetailResponses = webClient.get()
                .uri("?fields=name,flags,capital,population")
                .retrieve()
                .bodyToFlux(CountryDetailResponse.class)
                .collectList()
                .timeout(Duration.ofSeconds(20))
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)))
                .block();

        assert countryDetailResponses != null;
        CountryDetailResponse countryDetailResponse = countryDetailResponses.stream().filter(country -> country.name().common().equalsIgnoreCase(countryName)).findFirst().orElse(null);
        assert countryDetailResponse != null;
        countryDetails = mapper.countryDetailsToCountryDetailResponse(countryDetailResponse);

        return countryDetails;
    }
}
