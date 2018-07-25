package ru.bellintegrator.practice.dictionary.country.dao;

import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;

import java.util.List;

/**
 * DAO для работы с Country
 */
public interface CountryDao {

    /**
     * Сохранить Country
     *
     * @param country
     */
    void saveCountry(Country country);

    /**
     * Вернуть список всех Country
     */
    List<Country> getAllCountries();
}
