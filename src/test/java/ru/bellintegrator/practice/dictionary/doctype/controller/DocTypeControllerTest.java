package ru.bellintegrator.practice.dictionary.doctype.controller;

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
 * Тест для проверки контроллера
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@DirtiesContext
public class DocTypeControllerTest {

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    int port;

    /**
     * Тест для проверки возвращения всех типов документов c методом GET
     */
    @Test
    public void getDocTypesGetMethod() {
        HttpEntity<String> entityWithNullBody = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURL("/docs"),
                HttpMethod.GET, entityWithNullBody, String.class);
        Assert.assertEquals("200", response.getStatusCode().toString());
    }

    /**
     * Тест для проверки возвращения всех типов документов c методом POST
     */
    @Test(expected = HttpClientErrorException.class)
    public void exceptionWithGetDocTypesPostMethod() {
        HttpEntity<String> entityWithNullBody = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURL("/docs"),
                HttpMethod.POST, entityWithNullBody, String.class);
    }

    private String createURL(String url) {
        return String.format("http://localhost:%s/api%s", port, url);
    }
}