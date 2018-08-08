package ru.bellintegrator.practice.dictionary.country.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.MapperFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.country.view.CountryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Маппер для Country
 */
public class CountryMapper {

    private MapperFactory mapperFactory;
    private List<CountryView> countryViewList;

    public CountryMapper() {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        countryViewList = new ArrayList<>();
    }

    /**
     * Маппер, который принимает список сущностей страны и превращает в список представлений страны
     */
    public List<CountryView> mapToCountryViewList(List<Country> countryList) {
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
