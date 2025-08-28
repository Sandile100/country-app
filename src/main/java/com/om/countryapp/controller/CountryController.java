package com.om.countryapp.controller;

import com.om.countryapp.model.Country;
import com.om.countryapp.model.CountryDetails;
import com.om.countryapp.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
      return  ResponseEntity.status(HttpStatus.OK).body(countryService.getAllCountries());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/countries/{name}")
    public ResponseEntity<List<CountryDetails>> getCountryDetails(@PathVariable String name) {
     return  ResponseEntity.status(HttpStatus.OK).body(countryService.getCountryDetails());
    }
}
