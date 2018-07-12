package ru.bellintegrator.practice.country.service;

import ru.bellintegrator.practice.country.view.CountryView;

/**
 * Сервис
 */
public interface CountryService {

    /**
     * Обновить страну
     *
     * @param countryView
     */
    void updateCountry(CountryView countryView);

}
