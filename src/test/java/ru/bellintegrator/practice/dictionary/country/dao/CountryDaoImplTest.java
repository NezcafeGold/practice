package ru.bellintegrator.practice.dictionary.country.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.dictionary.country.model.Country;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Тест ДАО
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "src/main/resources")
public class CountryDaoImplTest {

    @Autowired
    CountryDao countryDao;

    /**
     * Тест для проверки возвращения страны по существующему коду
     */
    @Test
    public void getCountryByAvailableCode() {
        String code = "643";
        Country country = countryDao.getCountryByCode(code);
        String expectedName = "Российская Федерация";
        String expectedCode = code;

        Assert.assertNotNull(country);
        Assert.assertEquals(expectedName, country.getName());
        Assert.assertEquals(expectedCode, country.getCode());
    }

    /**
     * Тест для проверки возвращения страны по несуществующему коду
     */
    @Test(expected = EmptyResultDataAccessException.class)
    public void getCountryByNotAvailableCode() {
        String wrongCode = "777";
        Country countryWithWrongCode = countryDao.getCountryByCode(wrongCode);
    }

    /**
     * Тест для проверки возвращения всех стран
     */
    @Test
    public void getAllCountries() {
        List<Country> countryList = countryDao.getAllCountries();
        Assert.assertEquals(5, countryList.size());
    }
}