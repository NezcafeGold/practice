package ru.bellintegrator.practice.dictionary.country.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dictionary.country.dao.CountryDao;
import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.country.view.CountryView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private final CountryDao countryDao;
    private CountryMapper countryMapper;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
        countryMapper = new CountryMapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<CountryView> getCountries() {
        List<Country> countryList = countryDao.getAllCountries();
        List<CountryView> countryViewList = countryMapper.mapToCountryViewList(countryList);
        return countryViewList;
    }
}
