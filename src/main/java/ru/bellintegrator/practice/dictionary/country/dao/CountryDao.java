package ru.bellintegrator.practice.dictionary.country.dao;

import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;

import java.util.List;

/**
 * DAO для работы с Country
 */
public interface CountryDao {

    /**
     * Найти Country по имени
     *
     * @param citizenshipCode
     */
    Country getCountryByCode(String citizenshipCode);

    /**
     * Вернуть все Country
     */
    List<Country> getAllCountries();
}
