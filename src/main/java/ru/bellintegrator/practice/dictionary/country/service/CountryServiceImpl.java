package ru.bellintegrator.practice.dictionary.country.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dictionary.country.view.CountryView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class CountryServiceImpl implements CountryService {

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<CountryView> getCountries() {
        return null;
    }
}
