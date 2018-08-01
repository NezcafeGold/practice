package ru.bellintegrator.practice.dictionary.country.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.dictionary.country.service.CountryService;
import ru.bellintegrator.practice.dictionary.country.view.CountryView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "Контроллер для управление данными организации")
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation(value = "Возвращает список стран", nickname = "getCountries", httpMethod = "GET")
    @GetMapping(value = "/countries")
    public List<CountryView> getCountries() {
        return countryService.getCountries();
    }
}
