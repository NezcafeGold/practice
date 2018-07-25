package ru.bellintegrator.practice.dictionary.country.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionary.country.model.Country;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class CountryDaoImpl implements CountryDao{
    @Override

    /**
     * {@inheritDoc}
     */
    public void saveCountry(Country country) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getAllCountries() {
        return null;
    }
}
