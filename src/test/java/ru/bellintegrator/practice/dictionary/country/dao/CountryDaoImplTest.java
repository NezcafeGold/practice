package ru.bellintegrator.practice.dictionary.country.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.country.view.CountryView;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "src/main/resources")
public class CountryDaoImplTest {

    @Autowired
    CountryDao countryDao;

    @Test
    public void getCountryByCode() {
        String code = "643";
        Country country = countryDao.getCountryByCode(code);
        String expectedName = "Российская Федерация";
        String expectedCode = code;

        Assert.assertNotNull(country);
        Assert.assertEquals(expectedName, country.getName());
        Assert.assertEquals(expectedCode, country.getCode());
    }

    @Test
    public void getAllCountries() {
        List<Country> countryList = countryDao.getAllCountries();
        int expectedCountries = 5;
        Assert.assertEquals(5, countryList.size());
    }
}