package ru.bellintegrator.practice.organization.controller;

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
import ru.bellintegrator.practice.organization.view.OrganizationView;

import static org.junit.Assert.*;

/**
 * Тест для проверки контроллера организации
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@Transactional
@DirtiesContext
public class OrganizationControllerTest {

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    int port;

    /**
     * Тест для проверки фильтра организации
     */
    @Test
    public void filterOrganization() {
        OrganizationView orgViewSuccess = new OrganizationView();
        orgViewSuccess.name = "Евросеть";
        HttpEntity<OrganizationView> entitySuccess = new HttpEntity(orgViewSuccess, headers);
        ResponseEntity<String> responseSuccess = restTemplate.exchange(
                createURL("/organization/list"),
                HttpMethod.POST, entitySuccess, String.class);
        String expectedValue = "{\"data\":[{\"id\":1,\"name\":\"Евросеть\",\"isActive\":true}]}";
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
        Assert.assertEquals(expectedValue, responseSuccess.getBody());

        OrganizationView orgViewNotFound = new OrganizationView();
        HttpEntity<OrganizationView> entityNotFound = new HttpEntity(orgViewNotFound, headers);
        try {
            ResponseEntity<String> responseNotFound = restTemplate.exchange(
                    createURL("/organization/list"),
                    HttpMethod.POST, entityNotFound, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }

        HttpEntity<OrganizationView> entityNull = new HttpEntity(null, headers);
        try {
            ResponseEntity<String> responseNull = restTemplate.exchange(
                    createURL("/organization/list"),
                    HttpMethod.POST, entityNull, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }
    }

    /**
     * Тест для проверки возвращения организации по id
     */
    @Test
    public void getOrganizationById() {
        HttpEntity<OrganizationView> entitySuccess = new HttpEntity(null, headers);
        ResponseEntity<String> responseSuccess = restTemplate.exchange(
                createURL("/organization/2"),
                HttpMethod.GET, entitySuccess, String.class);
        String expectedValue = "{\"data\":{\"id\":2,\"name\":\"DNS\",\"fullName\":\"ООО ДНС\",\"inn\":\"566401571\"," +
                "\"kpp\":\"644901001\",\"address\":\"ул. Достоевского, 42\",\"isActive\":true}}";
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
        Assert.assertEquals(expectedValue, responseSuccess.getBody());

        HttpEntity<OrganizationView> entityNotFound = new HttpEntity(null, headers);
        try {
            ResponseEntity<String> responseNotFound = restTemplate.exchange(
                    createURL("/user/14s5s"),
                    HttpMethod.GET, entityNotFound, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }
    }

    /**
     * Тест для проверки обновления организации
     */
    @Test
    public void updateOrganization() {
        OrganizationView orgViewSuccess = new OrganizationView();
        orgViewSuccess.id = 1L;
        orgViewSuccess.name = "Мегафон";
        orgViewSuccess.fullName = "ПАО Мегафон";
        orgViewSuccess.inn = "424242";
        orgViewSuccess.kpp = "442424";
        orgViewSuccess.address = "ул. Пушкина, 22";
        HttpEntity<OrganizationView> entitySuccess = new HttpEntity(orgViewSuccess, headers);
        ResponseEntity<String> responseSuccess = restTemplate.exchange(
                createURL("/organization/update"),
                HttpMethod.POST, entitySuccess, String.class);
        Assert.assertEquals("{\"data\":{\"result\":\"success\"}}", responseSuccess.getBody());
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());

        OrganizationView orgViewNotFound = new OrganizationView();
        orgViewNotFound.id = 1L;
        HttpEntity<OrganizationView> entityNotFound = new HttpEntity(orgViewNotFound, headers);
        try {
            ResponseEntity<String> responseNotFound = restTemplate.exchange(
                    createURL("/organization/update"),
                    HttpMethod.POST, entityNotFound, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }

        HttpEntity<OrganizationView> entityNull = new HttpEntity(null, headers);
        try {
            ResponseEntity<String> responseNull = restTemplate.exchange(
                    createURL("/organization/update"),
                    HttpMethod.POST, entityNull, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }
    }

    /**
     * Тест для проверки сохранения организации
     */
    @Test
    public void saveOrganization() {
        OrganizationView orgViewSuccess = new OrganizationView();
        orgViewSuccess.name = "Мегафон";
        orgViewSuccess.fullName = "ПАО Мегафон";
        orgViewSuccess.inn = "424242";
        orgViewSuccess.kpp = "442424";
        orgViewSuccess.address = "ул. Пушкина, 22";
        HttpEntity<OrganizationView> entitySuccess = new HttpEntity(orgViewSuccess, headers);
        ResponseEntity<String> responseSuccess = restTemplate.exchange(
                createURL("/organization/save"),
                HttpMethod.POST, entitySuccess, String.class);
        Assert.assertEquals("{\"data\":{\"result\":\"success\"}}", responseSuccess.getBody());
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());

        OrganizationView orgViewNotFound = new OrganizationView();
        orgViewNotFound.id = 1L;
        HttpEntity<OrganizationView> entityNotFound = new HttpEntity(orgViewNotFound, headers);
        try {
            ResponseEntity<String> responseNotFound = restTemplate.exchange(
                    createURL("/organization/save"),
                    HttpMethod.POST, entityNotFound, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }

        HttpEntity<OrganizationView> entityNull = new HttpEntity(null, headers);
        try {
            ResponseEntity<String> responseNull = restTemplate.exchange(
                    createURL("/organization/save"),
                    HttpMethod.POST, entityNull, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }
    }

    private String createURL(String url) {
        return "http://localhost:" + port + "/api" + url;
    }
}