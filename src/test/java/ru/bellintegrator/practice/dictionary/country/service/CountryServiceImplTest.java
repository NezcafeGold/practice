package ru.bellintegrator.practice.dictionary.country.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.bellintegrator.practice.dictionary.country.dao.CountryDao;
import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.country.view.CountryView;

import java.util.ArrayList;
import java.util.List;


/**
 * Тест для проверки сервиса
 */
@RunWith(MockitoJUnitRunner.class)
public class CountryServiceImplTest {

    @Mock
    CountryDao countryDao;

    @InjectMocks
    CountryServiceImpl countryService;

    /**
     * Тест для проверки возврата всех стран
     */
    @Test
    public void getCountries() {
        List<Country> countryListMock = createList();
        Mockito.when(countryDao.getAllCountries()).thenReturn(countryListMock);
        List<CountryView> actualList = countryService.getCountries();

        Assert.assertNotNull(actualList);
        Assert.assertEquals(2, actualList.size());
        Assert.assertEquals("21", actualList.get(0).code);
        Assert.assertEquals("Россия", actualList.get(0).name);
    }

    private List<Country> createList() {
        List<Country> countryList = new ArrayList<>();
        countryList.add(new Country("Россия", "21"));
        countryList.add(new Country("Украина", "804"));
        return countryList;
    }
}