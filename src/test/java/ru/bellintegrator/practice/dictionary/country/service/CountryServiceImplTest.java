package ru.bellintegrator.practice.dictionary.country.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.dictionary.country.dao.CountryDao;
import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.country.view.CountryView;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
public class CountryServiceImplTest {

    @Mock
    CountryDao countryDao;

    @InjectMocks
    CountryServiceImpl countryService;

    @Test
    public void getCountries() {
        ArgumentCaptor<Country> argument = ArgumentCaptor.forClass(Country.class);
        List<Country> countryList = new ArrayList<>();
        Country country = new Country("Россия", "21");
        Country country1 = new Country("Украина", "804");
        countryList.add(country);
        countryList.add(country1);

        Mockito.when(countryDao.getAllCountries()).thenReturn(countryList);
        List<CountryView> actualList = countryService.getCountries();
        Assert.assertEquals(2, actualList.size());
        Assert.assertEquals("21", actualList.get(0).code);
        Assert.assertEquals("Россия", actualList.get(0).name);
    }
}