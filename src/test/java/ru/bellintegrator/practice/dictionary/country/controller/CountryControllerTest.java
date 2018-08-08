package ru.bellintegrator.practice.dictionary.country.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.bellintegrator.practice.Application;


/**
 * Тест контроллера
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@DirtiesContext
public class CountryControllerTest {

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    int port;

    /**
     * Тест для проверки возвращения списка стран
     */
    @Test
    public void getCountries() {
        HttpEntity<String> entityNull = new HttpEntity(null, headers);
        ResponseEntity<String> responseNull = restTemplate.exchange(
                createURL("/countries"),
                HttpMethod.GET, entityNull, String.class);
        Assert.assertEquals("200", responseNull.getStatusCode().toString());
    }

    private String createURL(String url) {
        return "http://localhost:" + port + "/api" + url;
    }
}