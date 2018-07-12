package ru.bellintegrator.practice.country.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.country.service.CountryService;
import ru.bellintegrator.practice.country.view.CountryView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation(value = "updateCountry", nickname = "updateCountry", httpMethod = "POST")
    @PostMapping(value = "/countries")
    public void updateDocs(@RequestBody CountryView countryView) {
        countryService.updateCountry(countryView);
    }
}
