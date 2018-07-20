package ru.bellintegrator.practice.dictionary.country.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.dictionary.country.service.CountryService;
import ru.bellintegrator.practice.dictionary.country.view.CountryView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation(value = "getCountries", nickname = "getCountries", httpMethod = "GET")
    @PostMapping(value = "/countries")
    public List<CountryView> getCountries() {
        return countryService.getCountries();
    }
}