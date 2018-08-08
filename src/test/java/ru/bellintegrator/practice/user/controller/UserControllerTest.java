package ru.bellintegrator.practice.user.controller;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.bellintegrator.practice.Application;

import ru.bellintegrator.practice.user.view.UserView;

/**
 * Тест для проверки контроллера пользователя
 */
@RunWith(SpringRunner.class)
@DirtiesContext
public class UserControllerTest {

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    int port;

    /**
     * Тест для проверки фильтра пользователя
     */
    @Test
    public void filterUser() {
        UserView userViewSuccess = new UserView();
        userViewSuccess.officeId = 4L;
        HttpEntity<UserView> entitySuccess = new HttpEntity(userViewSuccess, headers);
        ResponseEntity<String> responseSuccess = restTemplate.exchange(
                createURL("/user/list"),
                HttpMethod.POST, entitySuccess, String.class);
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());

        UserView userViewSuccessFull = new UserView();
        userViewSuccessFull.officeId = 4L;
        userViewSuccessFull.firstName = "Вячеслав";
        userViewSuccessFull.secondName = "Андреев";
        HttpEntity<UserView> entitySuccessFull = new HttpEntity(userViewSuccessFull, headers);
        ResponseEntity<String> responseSuccessFull = restTemplate.exchange(
                createURL("/user/list"),
                HttpMethod.POST, entitySuccessFull, String.class);
        String expectedBody = "{\"data\":[{\"id\":5,\"firstName\":\"Вячеслав\",\"secondName\":\"Андреев\",\"middleName\":\"Романович\",\"position\":\"Продавец\"}]}";
        Assert.assertEquals(expectedBody, responseSuccessFull.getBody());

        UserView userViewNotFound = new UserView();
        HttpEntity<UserView> entityNotFound = new HttpEntity(userViewNotFound, headers);
        try {
            ResponseEntity<String> responseNotFound = restTemplate.exchange(
                    createURL("/user/list"),
                    HttpMethod.POST, entityNotFound, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }

        HttpEntity<UserView> entityNull = new HttpEntity(null, headers);
        try {
            ResponseEntity<String> responseNull = restTemplate.exchange(
                    createURL("/user/list"),
                    HttpMethod.POST, entityNull, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }
    }

    /**
     * Тест для проверки возвращения пользователя по id
     */
    @Test
    public void getUserById() {
        HttpEntity<UserView> entitySuccess = new HttpEntity(null, headers);
        ResponseEntity<String> responseSuccess = restTemplate.exchange(
                createURL("/user/1"),
                HttpMethod.GET, entitySuccess, String.class);
        String expectedBody = "{\"data\":{\"id\":1,\"firstName\":\"Михаил\",\"secondName\":\"Уточкин\"," +
                "\"middleName\":\"Петрович\",\"position\":\"Директор\",\"phone\":\"89275588712\"," +
                "\"docName\":\"Паспорт гражданина Российской Федерации\",\"docNumber\":\"8345654879\"," +
                "\"docDate\":\"21.05.05\",\"citizenshipName\":\"Российская Федерация\",\"citizenshipCode\":\"643\"," +
                "\"isIdentified\":true}}";
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
        Assert.assertEquals(expectedBody, responseSuccess.getBody());

        HttpEntity<UserView> entityNotFound = new HttpEntity(null, headers);
        try {
            ResponseEntity<String> responseNotFound = restTemplate.exchange(
                    createURL("/user/14s5s"),
                    HttpMethod.GET, entityNotFound, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }
    }

    /**
     * Тест для проверки обновления пользователя
     */
    @Test
    public void updateUser() {
        UserView userViewSuccess = new UserView();
        userViewSuccess.id = 5L;
        userViewSuccess.firstName = "Геннадий";
        userViewSuccess.position = "Директор";
        HttpEntity<UserView> entitySuccess = new HttpEntity(userViewSuccess, headers);
        ResponseEntity<String> responseSuccess = restTemplate.exchange(
                createURL("/user/update"),
                HttpMethod.POST, entitySuccess, String.class);
        Assert.assertEquals("{\"data\":{\"result\":\"success\"}}", responseSuccess.getBody());
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());

        UserView userViewNotFound = new UserView();
        userViewSuccess.id = 1L;
        HttpEntity<UserView> entityNotFound = new HttpEntity(userViewNotFound, headers);
        try {
            ResponseEntity<String> responseNotFound = restTemplate.exchange(
                    createURL("/user/update"),
                    HttpMethod.POST, entityNotFound, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }

        HttpEntity<UserView> entityNull = new HttpEntity(null, headers);
        try {
            ResponseEntity<String> responseNull = restTemplate.exchange(
                    createURL("/user/update"),
                    HttpMethod.POST, entityNull, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }
    }

    /**
     * Тест для проверки сохранения пользователя
     */
    @Test
    public void saveUser() {
        UserView userViewSuccess = new UserView();
        userViewSuccess.firstName = "Геннадий";
        userViewSuccess.position = "Директор";
        HttpEntity<UserView> entitySuccess = new HttpEntity(userViewSuccess, headers);
        ResponseEntity<String> responseSuccess = restTemplate.exchange(
                createURL("/user/save"),
                HttpMethod.POST, entitySuccess, String.class);
        Assert.assertEquals("{\"data\":{\"result\":\"success\"}}", responseSuccess.getBody());
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());

        UserView userViewNotFound = new UserView();
        userViewSuccess.firstName = "Геннадий";
        HttpEntity<UserView> entityNotFound = new HttpEntity(userViewNotFound, headers);
        try {
            ResponseEntity<String> responseNotFound = restTemplate.exchange(
                    createURL("/user/update"),
                    HttpMethod.POST, entityNotFound, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }

        HttpEntity<UserView> entityNull = new HttpEntity(null, headers);
        try {
            ResponseEntity<String> responseNull = restTemplate.exchange(
                    createURL("/user/save"),
                    HttpMethod.POST, entityNull, String.class);
        } catch (HttpClientErrorException e) {
            Assert.assertEquals("404 Not Found", e.getMessage());
        }
    }

    private String createURL(String url) {
        return "http://localhost:" + port + "/api" + url;
    }
}