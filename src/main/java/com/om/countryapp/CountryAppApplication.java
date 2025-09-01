package com.om.countryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CountryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountryAppApplication.class, args);
    }

}
