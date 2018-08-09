package ru.bellintegrator.practice.office.controller;

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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.response.SuccessView;


/**
 * Тест для проверки контроллера Офиса
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@DirtiesContext
public class OfficeControllerTest {

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    int port;

    /**
     * Тест для проверки фильтра с допустимыми данными
     */
    @Test
    public void filterOfficeWithSuccessResponse() {
        OfficeView officeViewSuccess = new OfficeView();
        officeViewSuccess.orgId = 3L;

        OfficeView officeViewExpected = createOfficeViewFilter();

        HttpEntity<OfficeView> entitySuccess = new HttpEntity(officeViewSuccess, headers);
        ResponseEntity<OfficeView[]> responseSuccess = restTemplate.exchange(
                createURL("/office/list"),
                HttpMethod.POST, entitySuccess, OfficeView[].class);

        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
        Assert.assertEquals(String.valueOf(officeViewExpected), String.valueOf(responseSuccess.getBody()[0]));

        ResponseEntity<OfficeView[]> postForEntity = restTemplate.postForEntity(createURL("/office/list"),
                officeViewSuccess, OfficeView[].class);
        Assert.assertEquals("200", postForEntity.getStatusCode().toString());
        Assert.assertEquals(String.valueOf(officeViewExpected), String.valueOf(postForEntity.getBody()[0]));
    }

    /**
     * Тест для проверки фильтра с неправильным методом GET
     */
    @Test(expected = HttpClientErrorException.class)
    public void filterOfficeGetMethod() {
        HttpEntity<String> entityWithNullBody = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURL("/office/list"),
                HttpMethod.GET, entityWithNullBody, String.class);
    }

    /**
     * Тест для проверки фильтра с пустым телом
     */
    @Test(expected = HttpClientErrorException.class)
    public void exceptionWithFilterOfficeEmptyBodeEntity() {

        HttpEntity<OfficeView> entityBadRequest = new HttpEntity(null, headers);
        ResponseEntity<String> responseBadRequest = restTemplate.exchange(
                createURL("/office/list"),
                HttpMethod.POST, entityBadRequest, String.class);
    }

    /**
     * Тест для проверки возвращения офиса по id с допустимым запросом
     */
    @Test
    public void getOfficeByIdWithSuccessResponse() {
        HttpEntity<OfficeView> entitySuccess = new HttpEntity(null, headers);
        ResponseEntity<OfficeView> responseSuccess = restTemplate.exchange(
                createURL("/office/1"),
                HttpMethod.GET, entitySuccess, OfficeView.class);
        OfficeView expectedView = createOfficeViewGetById();
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
        Assert.assertEquals(String.valueOf(expectedView), String.valueOf(responseSuccess.getBody()));
    }

    /**
     * Тест для проверки возвращения офиса по id с недопустимым методом POST
     */
    @Test(expected = HttpClientErrorException.class)
    public void getOfficeByIdPostMethod() {
        HttpEntity<String> entityWithNullBody = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURL("/office/1"),
                HttpMethod.POST, entityWithNullBody, String.class);
    }

    /**
     * Тест для проверки возвращения офиса по id с ошибкой клиента
     */
    @Test(expected = HttpClientErrorException.class)
    public void getOfficeByIdNotAvailable() {
        HttpEntity<OfficeView> entityBadRequest = new HttpEntity(null, headers);
        ResponseEntity<String> responseBadRequest = restTemplate.exchange(
                createURL("/office/1notnumber"),
                HttpMethod.GET, entityBadRequest, String.class);
    }

    /**
     * Тест для проверки обновления офиса c допустимым запросом
     */
    @Test
    public void updateOfficeWithSuccessResponse() {
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
    }

    /**
     * Тест для проверки обновления офиса c недопустимым методом GET
     */
    @Test(expected = HttpClientErrorException.class)
    public void updateOfficeGetMethod() {
        HttpEntity<String> entityWithNullBody = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURL("/office/update"),
                HttpMethod.GET, entityWithNullBody, String.class);
    }

    /**
     * Тест для проверки обновления офиса, которого не существует
     */
    @Test(expected = HttpClientErrorException.class)
    public void updateOfficeWithNotAvailableId() {
        OfficeView officeViewNotFound = new OfficeView();
        officeViewNotFound.id = 5L;
        HttpEntity<OfficeView> entityNotFound = new HttpEntity(officeViewNotFound, headers);
        ResponseEntity<String> responseNotFound = restTemplate.exchange(
                createURL("/office/update"),
                HttpMethod.POST, entityNotFound, String.class);
    }

    /**
     * Тест для проверки обновления офиса c пустым телом запроса
     */
    @Test(expected = HttpClientErrorException.class)
    public void updateOfficeWithEmptyBodyResponse() {
        HttpEntity<OfficeView> entityNull = new HttpEntity(null, headers);
        ResponseEntity<String> responseNull = restTemplate.exchange(
                createURL("/office/update"),
                HttpMethod.POST, entityNull, String.class);
    }

    /**
     * Тест для проверки сохранения офиса c допустимым запросом
     */
    @Test
    public void saveOfficeWithSuccessResponse() {
        OfficeView officeViewSuccess = new OfficeView();
        officeViewSuccess.name = "Пушкинский";
        officeViewSuccess.address = "ул. Пушкина";
        HttpEntity<OfficeView> entitySuccess = new HttpEntity(officeViewSuccess, headers);
        ResponseEntity<String> responseSuccess = restTemplate.exchange(
                createURL("/office/save"),
                HttpMethod.POST, entitySuccess, String.class);
        Assert.assertEquals("{\"data\":{\"result\":\"success\"}}", responseSuccess.getBody());
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
    }

    /**
     * Тест для проверки сохранения офиса c недопустимым методом GET
     */
    @Test(expected = HttpClientErrorException.class)
    public void saveOfficeGetMethod() {
        HttpEntity<String> entityWithNullBody = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURL("/office/save"),
                HttpMethod.GET, entityWithNullBody, String.class);
    }

    /**
     * Тест для проверки сохранения офиса c неполным телом запроса
     */
    @Test(expected = HttpClientErrorException.class)
    public void saveOfficeWithNotAvailableResponse() {
        OfficeView officeView = new OfficeView();
        officeView.id = 5L;
        HttpEntity<OfficeView> entity = new HttpEntity(officeView, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURL("/office/save"),
                HttpMethod.POST, entity, String.class);
    }

    /**
     * Тест для проверки сохранения офиса c пустым телом запроса
     */
    @Test(expected = HttpClientErrorException.class)
    public void saveOfficeWithEmptyBodyResponse() {
        HttpEntity<OfficeView> entityNull = new HttpEntity(null, headers);
        ResponseEntity<String> responseNull = restTemplate.exchange(
                createURL("/office/save"),
                HttpMethod.POST, entityNull, String.class);
    }

    private String createURL(String url) {
        return String.format("http://localhost:%s/api%s", port, url);
    }

    private OfficeView createOfficeViewFilter() {
        OfficeView officeView = new OfficeView();
        officeView.id = 7L;
        officeView.name = "Торговый центр";
        officeView.isActive = true;
        return officeView;
    }

    private OfficeView createOfficeViewGetById() {
        OfficeView officeView = new OfficeView();
        officeView.id = 1L;
        officeView.phone = "89275588796";
        officeView.name = "Центральный";
        officeView.address = "ул. Пушкина, 49";
        officeView.isActive = true;
        return officeView;
    }
}