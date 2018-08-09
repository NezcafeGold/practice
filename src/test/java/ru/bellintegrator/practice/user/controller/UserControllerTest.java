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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;
import ru.bellintegrator.practice.response.SuccessView;
import ru.bellintegrator.practice.user.model.Document;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

/**
 * Тест для проверки контроллера пользователя
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@Transactional
@DirtiesContext
public class UserControllerTest {

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    int port;

    /**
     * Тест для проверки фильтра пользователей по параметру officeId
     */
    @Test
    public void filterUserWithId() {
        UserView userViewSuccess = new UserView();
        userViewSuccess.officeId = 4L;
        HttpEntity<UserView> entitySuccess = new HttpEntity(userViewSuccess, headers);
        ResponseEntity<UserView[]> responseSuccess = restTemplate.exchange(
                createURL("/user/list"),
                HttpMethod.POST, entitySuccess, UserView[].class);
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());

        ResponseEntity<UserView[]> postForEntity = restTemplate.postForEntity(createURL("/organization/list"),
                entitySuccess, UserView[].class);
        Assert.assertEquals("200", postForEntity.getStatusCode().toString());
    }

    /**
     * Тест для проверки фильтра пользователя по всем полям
     */
    @Test
    public void filterUserWithAllFields() {
        UserView userViewSuccessFull = new UserView();
        userViewSuccessFull.officeId = 4L;
        userViewSuccessFull.firstName = "Вячеслав";
        userViewSuccessFull.secondName = "Андреев";
        HttpEntity<UserView> entitySuccessFull = new HttpEntity(userViewSuccessFull, headers);
        ResponseEntity<UserView[]> responseSuccessFull = restTemplate.exchange(
                createURL("/user/list"),
                HttpMethod.POST, entitySuccessFull, UserView[].class);
        UserView userView = createUserViewForFilter();
        Assert.assertEquals(String.valueOf(userView), String.valueOf(responseSuccessFull.getBody()[0]));
    }

    /**
     * Тест для проверки фильтра пользователя с пустыми полями
     */
    @Test(expected = HttpClientErrorException.class)
    public void filterUserWithNoFields() {
        UserView userViewNotFound = new UserView();
        HttpEntity<UserView> entityNotFound = new HttpEntity(userViewNotFound, headers);
        ResponseEntity<UserView[]> responseNotFound = restTemplate.exchange(
                createURL("/user/list"),
                HttpMethod.POST, entityNotFound, UserView[].class);
    }

    /**
     * Тест для проверки фильтра пользователя с пустыми телом запроса
     */
    @Test(expected = HttpClientErrorException.class)
    public void filterUserWithEmptyBodyResponse() {
        HttpEntity entityNull = new HttpEntity(null, headers);
        ResponseEntity<UserView[]> responseNull = restTemplate.exchange(
                createURL("/user/list"),
                HttpMethod.POST, entityNull, UserView[].class);
    }

    /**
     * Тест для проверки фильтра пользователя с недопустимым методом GET
     */
    @Test(expected = HttpClientErrorException.class)
    public void filterUserWithGetMethod() {
        HttpEntity<UserView> entityNull = new HttpEntity(null, headers);
        ResponseEntity<UserView[]> responseNull = restTemplate.exchange(
                createURL("/user/list"),
                HttpMethod.GET, entityNull, UserView[].class);
    }

    /**
     * Тест для проверки возвращения пользователя по id
     */
    @Test
    public void getUserById() {
        HttpEntity<UserView> entitySuccess = new HttpEntity(null, headers);
        ResponseEntity<UserView> responseSuccess = restTemplate.exchange(
                createURL("/user/1"),
                HttpMethod.GET, entitySuccess, UserView.class);
        UserView actualUserView = createUserViewGetByIdGetById();
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
        Assert.assertEquals(String.valueOf(actualUserView), String.valueOf(responseSuccess.getBody()));
    }

    /**
     * Тест для проверки возвращения пользователя по некорректному id
     */
    @Test(expected = HttpClientErrorException.class)
    public void getUserByNotAvailableId() {
        HttpEntity<UserView> entityNotFound = new HttpEntity(null, headers);
        ResponseEntity<UserView[]> responseNotFound = restTemplate.exchange(
                createURL("/user/14s5s"),
                HttpMethod.GET, entityNotFound, UserView[].class);
    }

    /**
     * Тест для проверки обновления пользователя с запрашиваемыми полями
     */
    @Test
    public void updateUser() {
        UserView userViewSuccess = new UserView();
        userViewSuccess.id = 5L;
        userViewSuccess.firstName = "Геннадий";
        userViewSuccess.position = "Директор";
        HttpEntity<UserView> entitySuccess = new HttpEntity(userViewSuccess, headers);
        ResponseEntity<SuccessView> responseSuccess = restTemplate.exchange(
                createURL("/user/update"),
                HttpMethod.POST, entitySuccess, SuccessView.class);
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
    }

    /**
     * Тест для проверки обновления пользователя с частью запрашиваемых полей
     */
    @Test(expected = HttpClientErrorException.class)
    public void updateUserWithPartOfRequestedFields() {
        UserView userView = new UserView();
        userView.id = 1L;
        HttpEntity<UserView> entityNotFound = new HttpEntity(userView, headers);
        ResponseEntity<SuccessView> responseNotFound = restTemplate.exchange(
                createURL("/user/update"),
                HttpMethod.POST, entityNotFound, SuccessView.class);
    }

    /**
     * Тест для проверки обновления пользователя с пустым телом запроса
     */
    @Test(expected = HttpClientErrorException.class)
    public void updateUserWithNullBodyResponse() {
        HttpEntity<UserView> entityNull = new HttpEntity(null, headers);
        ResponseEntity<SuccessView> responseNull = restTemplate.exchange(
                createURL("/user/update"),
                HttpMethod.POST, entityNull, SuccessView.class);
    }

    /**
     * Тест для проверки обновления пользователя с недопустимым методом GET
     */
    @Test(expected = HttpClientErrorException.class)
    public void updateUserGetMethod() {
        HttpEntity<UserView> entityNull = new HttpEntity(null, headers);
        ResponseEntity<SuccessView> responseNull = restTemplate.exchange(
                createURL("/user/update"),
                HttpMethod.GET, entityNull, SuccessView.class);
    }

    /**
     * Тест для проверки сохранения пользователя
     */
    @Test
    public void saveUserWithRequestedFields() {
        UserView userViewSuccess = new UserView();
        userViewSuccess.firstName = "Геннадий";
        userViewSuccess.position = "Директор";
        HttpEntity<UserView> entitySuccess = new HttpEntity(userViewSuccess, headers);
        ResponseEntity<SuccessView> responseSuccess = restTemplate.exchange(
                createURL("/user/save"),
                HttpMethod.POST, entitySuccess, SuccessView.class);
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
    }

    /**
     * Тест для проверки сохранения пользователя c частью запрашиваемых полей
     */
    @Test(expected = HttpClientErrorException.class)
    public void saveUserWithPartOfRequestedFields() {
        UserView userView = new UserView();
        userView.firstName = "Геннадий";
        HttpEntity<UserView> entityNotFound = new HttpEntity(userView, headers);
        ResponseEntity<SuccessView> responseNotFound = restTemplate.exchange(
                createURL("/user/update"),
                HttpMethod.POST, entityNotFound, SuccessView.class);
    }

    /**
     * Тест для проверки сохранения пользователя c пустым телом запроса
     */
    @Test(expected = HttpClientErrorException.class)
    public void saveUserWithNullBodyResponse() {
        HttpEntity<UserView> entity = new HttpEntity(null, headers);
        ResponseEntity<SuccessView> response = restTemplate.exchange(
                createURL("/user/save"),
                HttpMethod.POST, entity, SuccessView.class);
    }

    /**
     * Тест для проверки сохранения пользователя c недопустимым методом GET
     */
    @Test(expected = HttpClientErrorException.class)
    public void saveUserGetMethod() {
        HttpEntity<UserView> entityNull = new HttpEntity(null, headers);
        ResponseEntity<SuccessView> responseNull = restTemplate.exchange(
                createURL("/user/save"),
                HttpMethod.GET, entityNull, SuccessView.class);
    }

    private String createURL(String url) {
        return String.format("http://localhost:%s/api%s", port, url);
    }

    private UserView createUserViewForFilter() {
        UserView userView = new UserView();
        userView.id = 5L;
        userView.firstName = "Вячеслав";
        userView.secondName = "Андреев";
        userView.middleName = "Романович";
        userView.position = "Продавец";
        return userView;
    }

    private UserView createUserViewGetByIdGetById() {
        UserView userView = new UserView();
        userView.id = 1L;
        userView.firstName = "Михаил";
        userView.secondName = "Уточкин";
        userView.middleName = "Петрович";
        userView.position = "Директор";
        userView.phone = "89275588712";
        userView.isIdentified = true;
        userView.docName = "Паспорт гражданина Российской Федерации";
        userView.docNumber = "8345654879";
        userView.docDate = "21.05.05";
        userView.citizenshipCode = "643";
        userView.citizenshipName = "Российская Федерация";
        return userView;
    }

    private User createUser() {
        User user = new User(1L, "Михаил", "Уточкин", "Петрович", "Директор", "89275588712", true);
        DocType docType = new DocType("Паспорт гражданина Российской Федерации", "21");
        Country country = new Country("Российская Федерация", "643");
        Document document = new Document("8345654879", "21.05.05", docType, country);
        user.setDocument(document);
        return user;
    }
}