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
import ru.bellintegrator.practice.response.SuccessView;

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
    public void filterOrganizationSuccessResponse() {
        OrganizationView orgViewSuccess = new OrganizationView();
        orgViewSuccess.name = "Евросеть";
        HttpEntity<OrganizationView> entitySuccess = new HttpEntity(orgViewSuccess, headers);
        ResponseEntity<OrganizationView[]> responseSuccess = restTemplate.exchange(
                createURL("/organization/list"),
                HttpMethod.POST, entitySuccess, OrganizationView[].class);
        OrganizationView expectedValue = createOrgViewForFilter();
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
        OrganizationView actualOrgViewFromExchange = responseSuccess.getBody()[0];
        Assert.assertEquals(expectedValue.id, actualOrgViewFromExchange.id);
        Assert.assertEquals(expectedValue.name, actualOrgViewFromExchange.name);
        Assert.assertEquals(expectedValue.isActive, actualOrgViewFromExchange.isActive);

        ResponseEntity<OrganizationView[]> postForEntity = restTemplate.postForEntity(createURL("/organization/list"),
                entitySuccess, OrganizationView[].class);
        Assert.assertEquals("200", postForEntity.getStatusCode().toString());
        OrganizationView actualOrgView = postForEntity.getBody()[0];
        Assert.assertEquals(expectedValue.id, actualOrgView.id);
        Assert.assertEquals(expectedValue.name, actualOrgView.name);
        Assert.assertEquals(expectedValue.isActive, actualOrgView.isActive);
    }

    /**
     * Тест для проверки фильтра организации c пустым телом запроса
     */
    @Test(expected = HttpClientErrorException.class)
    public void filterOrganizationWithNullBodyResponse() {
        HttpEntity entity = new HttpEntity(null, headers);
        ResponseEntity<OrganizationView[]> response = restTemplate.exchange(
                createURL("/organization/list"),
                HttpMethod.POST, entity, OrganizationView[].class);
    }

    /**
     * Тест для проверки фильтра организации c некорректным методом GET
     */
    @Test(expected = HttpClientErrorException.class)
    public void filterOrganizationGetMethod() {
        HttpEntity entity = new HttpEntity(null, headers);
        ResponseEntity<SuccessView> response = restTemplate.exchange(
                createURL("/organization/list"),
                HttpMethod.GET, entity, SuccessView.class);
    }

    /**
     * Тест для проверки возвращения организации c корректным id
     */
    @Test
    public void getOrganizationByIdWithAvailableId() {
        HttpEntity entitySuccess = new HttpEntity(null, headers);
        ResponseEntity<OrganizationView> responseSuccess = restTemplate.exchange(
                createURL("/organization/2"),
                HttpMethod.GET, entitySuccess, OrganizationView.class);
        OrganizationView expectedOrgView = createOrgViewGetById();
        OrganizationView actualOrgView = responseSuccess.getBody();
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
        Assert.assertEquals(expectedOrgView.id, actualOrgView.id);
        Assert.assertEquals(expectedOrgView.name, actualOrgView.name);
        Assert.assertEquals(expectedOrgView.isActive, actualOrgView.isActive);
    }

    /**
     * Тест для проверки возвращения организации c некорректным id
     */
    @Test(expected = HttpClientErrorException.class)
    public void getOrganizationByIdWithNotAvailableId() {
        HttpEntity entityNotFound = new HttpEntity(null, headers);
        ResponseEntity<OrganizationView> responseNotFound = restTemplate.exchange(
                createURL("/user/14s5s"),
                HttpMethod.GET, entityNotFound, OrganizationView.class);
    }

    /**
     * Тест для проверки возвращения организации по id с недоступным методом POST
     */
    @Test(expected = HttpClientErrorException.class)
    public void getOrganizationByIdPostMethod() {
        HttpEntity entityNotFound = new HttpEntity(null, headers);
        ResponseEntity<OrganizationView> responseNotFound = restTemplate.exchange(
                createURL("/user/14s5s"),
                HttpMethod.POST, entityNotFound, OrganizationView.class);
    }

    /**
     * Тест для проверки обновления организации
     */
    @Test
    public void updateOrganizationSuccessResponse() {
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
    }

    /**
     * Тест для проверки обновления организации c неполными данными
     */
    @Test(expected = HttpClientErrorException.class)
    public void updateOrganizationWithPartOfRequestedFields() {
        OrganizationView orgView = new OrganizationView();
        orgView.id = 1L;
        HttpEntity<OrganizationView> entity = new HttpEntity(orgView, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURL("/organization/update"),
                HttpMethod.POST, entity, String.class);
    }

    /**
     * Тест для проверки обновления организации c пустым телом запроса
     */
    @Test(expected = HttpClientErrorException.class)
    public void updateOrganizationWithNullBodyResponse() {
        HttpEntity entityNull = new HttpEntity(null, headers);
        ResponseEntity<SuccessView> responseNull = restTemplate.exchange(
                createURL("/organization/update"),
                HttpMethod.POST, entityNull, SuccessView.class);
    }

    /**
     * Тест для проверки обновления организации c недопустимым методом GET
     */
    @Test(expected = HttpClientErrorException.class)
    public void updateOrganizationGetMethod() {
        HttpEntity entityNull = new HttpEntity(null, headers);
        ResponseEntity<SuccessView> responseNull = restTemplate.exchange(
                createURL("/organization/update"),
                HttpMethod.GET, entityNull, SuccessView.class);
    }

    /**
     * Тест для проверки сохранения организации c требуемыми полями
     */
    @Test
    public void saveOrganizationSuccessResponse() {
        OrganizationView orgViewSuccess = new OrganizationView();
        orgViewSuccess.name = "Мегафон";
        orgViewSuccess.fullName = "ПАО Мегафон";
        orgViewSuccess.inn = "424242";
        orgViewSuccess.kpp = "442424";
        orgViewSuccess.address = "ул. Пушкина, 22";
        HttpEntity<OrganizationView> entitySuccess = new HttpEntity(orgViewSuccess, headers);
        ResponseEntity<SuccessView> responseSuccess = restTemplate.exchange(
                createURL("/organization/save"),
                HttpMethod.POST, entitySuccess, SuccessView.class);
        Assert.assertEquals("200", responseSuccess.getStatusCode().toString());
    }

    /**
     * Тест для проверки сохранения организации c частью требуемых полей
     */
    @Test(expected = HttpClientErrorException.class)
    public void saveOrganizationWithPartOfRequestedFields() {
        OrganizationView orgViewNotFound = new OrganizationView();
        orgViewNotFound.id = 1L;
        HttpEntity<OrganizationView> entityNotFound = new HttpEntity(orgViewNotFound, headers);
            ResponseEntity<SuccessView> responseNotFound = restTemplate.exchange(
                    createURL("/organization/save"),
                    HttpMethod.POST, entityNotFound, SuccessView.class);
    }

    /**
     * Тест для проверки сохранения организации c частью требуемых полей
     */
    @Test(expected = HttpClientErrorException.class)
    public void saveOrganizationWithNullBodyResponse() {
        HttpEntity<OrganizationView> entityNull = new HttpEntity(null, headers);
        ResponseEntity<SuccessView> responseNull = restTemplate.exchange(
                createURL("/organization/save"),
                HttpMethod.POST, entityNull, SuccessView.class);
    }

    private String createURL(String url) {
        return String.format("http://localhost:%s/api%s", port, url);
    }

    private OrganizationView createOrgViewForFilter() {
        OrganizationView orgView = new OrganizationView();
        orgView.id = 1L;
        orgView.name = "Евросеть";
        orgView.isActive = true;
        return orgView;
    }

    private OrganizationView createOrgViewGetById() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.id = 2L;
        organizationView.name = "DNS";
        organizationView.fullName = "ООО ДНС";
        organizationView.inn = "566401571";
        organizationView.kpp = "644901001";
        organizationView.address = "ул. Достоевского, 42";
        organizationView.isActive = true;
        return organizationView;
    }
}