package ru.bellintegrator.practice.dictionary.country.service;

import ru.bellintegrator.practice.dictionary.country.view.CountryView;

import java.util.List;

/**
 * Сервис
 */
public interface CountryService {

    /**
     * Получить список стран
     *
     */
    List<CountryView> getCountries();

}
