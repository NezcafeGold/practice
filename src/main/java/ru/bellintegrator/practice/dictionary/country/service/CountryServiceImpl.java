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
    private MapperFactory mapperFactory;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<CountryView> getCountries() {
        List<CountryView> countryViewList = new ArrayList<>();
        List<Country> countryList = countryDao.getAllCountries();

        mapperFactory = new DefaultMapperFactory.Builder().build();
        for (int i = 0; i < countryList.size(); i++) {
            mapperFactory.classMap(Country.class, CountryView.class)
                    .field("name", "name")
                    .field("code", "code")
                    .byDefault()
                    .register();
            MapperFacade mapper = mapperFactory.getMapperFacade();
            CountryView countryView = mapper.map(countryList.get(i), CountryView.class);
            countryViewList.add(countryView);
        }
        return countryViewList;
    }
}
