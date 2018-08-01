package ru.bellintegrator.practice.organization.service;

import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "src/main/resources")
@Transactional
public class OrganizationServiceImplTest {

    @Mock
    OrganizationDao organizationDao;

    @InjectMocks
    OrganizationServiceImpl organizationService;

    @Test
    public void filterOrganization() {
        ArgumentCaptor<OrganizationView> argument = ArgumentCaptor.forClass(OrganizationView.class);
        OrganizationView organizationView = new OrganizationView();
        organizationView.name = "Евросеть";

        List<Organization> organizations = new ArrayList<>();
        Organization organizationFromDao = new Organization(1L, "Евросеть", "ПАО Евросеть", "74544", "121", "ул. Пушкина", "8971254545", true);
        organizations.add(organizationFromDao);

        Mockito.when(organizationDao.list(argument.capture())).thenReturn(organizations);
        List<OrganizationView> actualOrganizations = organizationService.filterOrganization(organizationView);
        Assert.assertEquals("Евросеть", argument.getValue().name);

        OrganizationView actualOrgView = actualOrganizations.get(0);
        Assert.assertEquals("Евросеть", actualOrgView.name);
        Assert.assertEquals("1", String.valueOf(actualOrgView.id));
        Assert.assertEquals(true, actualOrgView.isActive);
        Assert.assertNull(actualOrgView.address);
        Assert.assertNull(actualOrgView.fullName);
        Assert.assertNull(actualOrgView.inn);
        Assert.assertNull(actualOrgView.kpp);
        Assert.assertNull(actualOrgView.phone);
    }

    @Test
    public void getOrganizationById() {
        Organization organization = new Organization(1L, "Евросеть", "ПАО Евросеть", "74544", "121", "ул. Пушкина", "8971254545", true);

        Long id = 1L;
        Mockito.when(organizationDao.getOrganizationById(id)).thenReturn(organization);

        Long expectedId = 1L;
        String expectedName = "Евросеть";
        String expectedFullName = "ПАО Евросеть";
        String expectedInn = "74544";
        String expectedKpp = "121";
        String expectedAddress = "ул. Пушкина";
        String expectedPhone = "8971254545";
        Boolean expectedActive = true;

        OrganizationView actualOrgView = organizationService.getOrganizationById(id);
        Assert.assertNotNull(actualOrgView);
        Assert.assertEquals(expectedId, actualOrgView.id);
        Assert.assertEquals(expectedName, actualOrgView.name);
        Assert.assertEquals(expectedFullName, actualOrgView.fullName);
        Assert.assertEquals(expectedInn, actualOrgView.inn);
        Assert.assertEquals(expectedKpp, actualOrgView.kpp);
        Assert.assertEquals(expectedAddress, actualOrgView.address);
        Assert.assertEquals(expectedPhone, actualOrgView.phone);
        Assert.assertEquals(expectedActive, actualOrgView.isActive);

        try {
            organizationService.getOrganizationById(1222L);
        } catch (ServiceException e) {
            Assert.assertTrue(e.getMessage().equals("Организация с id 1222 не найдена"));
        }
    }

    @Test
    public void updateOrganization() {
        ArgumentCaptor<Organization> argument = ArgumentCaptor.forClass(Organization.class);
        OrganizationView organizationView = new OrganizationView(1L, "Мтс", "ПАО МТС", "74544",
                "121", "ул. Пушкина", "8971254545", true);
        OrganizationView organizationView2 = new OrganizationView();
        organizationView2.id = 1L;

        Organization organization = new Organization(1L, "Евросеть", "ПАО Евросеть", "74544",
                "121", "ул. Пушкина", "8971254545", true);

        Mockito.when(organizationDao.getOrganizationById(1L)).thenReturn(organization);

        organizationService.updateOrganization(organizationView);
        Mockito.verify(organizationDao).update(argument.capture());
        Assert.assertEquals("Мтс", argument.getValue().getName());
        Assert.assertEquals("ПАО МТС", argument.getValue().getFullName());
        Assert.assertEquals("74544", argument.getValue().getInn());
        Assert.assertEquals("121", argument.getValue().getKpp());
        Assert.assertEquals("ул. Пушкина", argument.getValue().getAddress());
        Assert.assertEquals("8971254545", argument.getValue().getPhone());
        Assert.assertEquals(true, argument.getValue().getIsActive());
        try {
            organizationService.updateOrganization(organizationView2);
        } catch (ServiceException e) {
            Assert.assertTrue(e.getMessage().equals("Не введен обязательный параметр name"));
        }

    }

    @Test
    public void saveOrganization() {
        ArgumentCaptor<Organization> argument = ArgumentCaptor.forClass(Organization.class);
        OrganizationView organizationView = new OrganizationView();
        organizationView.name = "МТС";
        organizationView.fullName = "ПАО МТС";
        organizationView.inn = "1561";
        organizationView.kpp = "4645646";
        organizationView.address = "4645646";
        organizationService.saveOrganization(organizationView);
        Mockito.verify(organizationDao).save(argument.capture());
        Assert.assertEquals("МТС", argument.getValue().getName());
    }
}