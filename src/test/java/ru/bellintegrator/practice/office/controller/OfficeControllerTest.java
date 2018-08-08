package ru.bellintegrator.practice.office.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.bellintegrator.practice.office.view.OfficeView;


/**
 * Тест для проверки контроллера Офиса
 */
@RunWith(SpringRunner.class)
public class OfficeControllerTest {

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    int port;

    /**
     * Тест для проверки фильтра
     */
    @Test
    public void filterOffice() {
        OfficeView officeViewSuccess = new OfficeView();
        officeViewSuccess.orgId = 3L;
        HttpEntity<OfficeView> entitySuccess = new HttpEntity(officeViewSuccess, headers);
        ResponseEntity<String> responseSuccess = restTemplate.exchange(
                createURL("/office/list"),
                HttpMethod.POST, entitySuccess, String.class);
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
        Assert.assertEquals("{\"data\":[{\"id\":7,\"name\":\"Торговый центр\",\"isActive\":true}]}",
                responseSuccess.getBody());

        HttpEntity<OfficeView> entityNull = new HttpEntity(null, headers);
        try {
            ResponseEntity<String> responseNull = restTemplate.exchange(
                    createURL("/office/list"),
                    HttpMethod.POST, entityNull, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }
    }

    /**
     * Тест для проверки возвращения офиса по id
     */
    @Test
    public void getOfficeById() {
        HttpEntity<OfficeView> entitySuccess = new HttpEntity(null, headers);
        ResponseEntity<String> responseSuccess = restTemplate.exchange(
                createURL("/office/1"),
                HttpMethod.GET, entitySuccess, String.class);
        String expectedBody = "{\"data\":{\"id\":1,\"name\":\"Центральный\",\"address\":\"ул. Пушкина, 49\"" +
                ",\"phone\":\"89275588796\",\"isActive\":true}}";
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
        Assert.assertEquals(expectedBody, responseSuccess.getBody());

        HttpEntity<OfficeView> entityNull = new HttpEntity(null, headers);
        try {
            ResponseEntity<String> responseNull = restTemplate.exchange(
                    createURL("/office/list"),
                    HttpMethod.POST, entityNull, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }
    }

    /**
     * Тест для проверки обновления офиса
     */
    @Test
    public void updateOffice() {
        OfficeView officeViewSuccess = new OfficeView();
        officeViewSuccess.id = 5L;
        officeViewSuccess.name = "Пушкинский";
        officeViewSuccess.address = "ул. Пушкина";
        HttpEntity<OfficeView> entitySuccess = new HttpEntity(officeViewSuccess, headers);
        ResponseEntity<String> responseSuccess = restTemplate.exchange(
                createURL("/office/update"),
                HttpMethod.POST, entitySuccess, String.class);
        Assert.assertEquals("{\"data\":{\"result\":\"success\"}}", responseSuccess.getBody());
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());

        OfficeView officeViewNotFound = new OfficeView();
        officeViewNotFound.id = 5L;
        HttpEntity<OfficeView> entityNotFound = new HttpEntity(officeViewNotFound, headers);
        try {
            ResponseEntity<String> responseNotFound = restTemplate.exchange(
                    createURL("/office/update"),
                    HttpMethod.POST, entityNotFound, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }

        HttpEntity<OfficeView> entityNull = new HttpEntity(null, headers);
        try {
            ResponseEntity<String> responseNull = restTemplate.exchange(
                    createURL("/office/update"),
                    HttpMethod.POST, entityNull, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }
    }

    /**
     * Тест для проверки сохранения офиса
     */
    @Test
    public void saveOffice() {
        OfficeView officeViewSuccess = new OfficeView();
        officeViewSuccess.name = "Пушкинский";
        officeViewSuccess.address = "ул. Пушкина";
        HttpEntity<OfficeView> entitySuccess = new HttpEntity(officeViewSuccess, headers);
        ResponseEntity<String> responseSuccess = restTemplate.exchange(
                createURL("/office/save"),
                HttpMethod.POST, entitySuccess, String.class);
        Assert.assertEquals("{\"data\":{\"result\":\"success\"}}", responseSuccess.getBody());
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());

        OfficeView officeViewNotFound = new OfficeView();
        officeViewNotFound.id = 5L;
        HttpEntity<OfficeView> entityNotFound = new HttpEntity(officeViewNotFound, headers);
        try {
            ResponseEntity<String> responseNotFound = restTemplate.exchange(
                    createURL("/office/save"),
                    HttpMethod.POST, entityNotFound, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }

        HttpEntity<OfficeView> entityNull = new HttpEntity(null, headers);
        try {
            ResponseEntity<String> responseNull = restTemplate.exchange(
                    createURL("/office/save"),
                    HttpMethod.POST, entityNull, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }
    }

    private String createURL(String url) {
        return "http://localhost:" + port + "/api" + url;
    }
}