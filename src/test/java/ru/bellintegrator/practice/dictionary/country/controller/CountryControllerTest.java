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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.response.SuccessView;


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
     * Тест для проверки возвращения списка стран c методом GET
     */
    @Test
    public void getCountriesGetMethod() {
        HttpEntity entityWithNullBody = new HttpEntity(null, headers);
        ResponseEntity<SuccessView> response = restTemplate.exchange(
                createURL("/countries"),
                HttpMethod.GET, entityWithNullBody, SuccessView.class);
        Assert.assertEquals("200", response.getStatusCode().toString());
    }

    /**
     * Тест для проверки возвращения списка стран c методом POST
     */
    @Test(expected = HttpClientErrorException.class)
    public void exceptionWithGetCountriesPostMethod() {
        HttpEntity entityWithNullBody = new HttpEntity(null, headers);
        ResponseEntity<SuccessView> response = restTemplate.exchange(
                createURL("/countries"),
                HttpMethod.POST, entityWithNullBody, SuccessView.class);
    }

    private String createURL(String url) {
        return String.format("http://localhost:%s/api%s", port, url);
    }
}