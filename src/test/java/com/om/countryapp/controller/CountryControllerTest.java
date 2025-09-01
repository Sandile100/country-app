package com.om.countryapp.controller;

import com.om.countryapp.model.Country;
import com.om.countryapp.model.CountryDetails;
import com.om.countryapp.service.CountryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountryControllerTest {

    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(countryService);
    }

    @Test
    void getAllCountries() {
        List<Country> expected = Collections.emptyList();
        when(countryService.getAllCountries()).thenReturn(expected);

        ResponseEntity<List<Country>> response = countryController.getAllCountries();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());

        verify(countryService, times(1)).getAllCountries();
    }

    @Test
    void getCountryDetails() {
        CountryDetails expected = new CountryDetails("any-name", 1000, "any-capital", "any-flag");
        when(countryService.getCountryDetails(any())).thenReturn(expected);

        ResponseEntity<CountryDetails> response = countryController.getCountryDetails("any-name");

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());

        verify(countryService, times(1)).getCountryDetails(any());
    }
}