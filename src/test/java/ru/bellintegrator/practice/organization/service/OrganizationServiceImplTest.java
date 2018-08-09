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
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.ArrayList;
import java.util.List;


/**
 * Тест для проверки сервиса организации
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizationServiceImplTest {

    @Mock
    OrganizationDao organizationDao;

    @InjectMocks
    OrganizationServiceImpl organizationService;

    /**
     * Тест для проверки фильтра организации
     */
    @Test
    public void filterOrganization() {
        ArgumentCaptor<OrganizationView> argument = ArgumentCaptor.forClass(OrganizationView.class);

        OrganizationView organizationViewMock = createOrganizationView();
        List<Organization> organizationListMock = createList();
        Mockito.when(organizationDao.list(argument.capture())).thenReturn(organizationListMock);
        List<OrganizationView> actualOrganizations = organizationService.filterOrganization(organizationViewMock);
        OrganizationView actualOrgView = actualOrganizations.get(0);

        Assert.assertEquals("Евросеть", argument.getValue().name);
        Assert.assertEquals("Евросеть", actualOrgView.name);
        Assert.assertEquals("1", String.valueOf(actualOrgView.id));
        Assert.assertEquals(true, actualOrgView.isActive);
        Assert.assertNull(actualOrgView.address);
        Assert.assertNull(actualOrgView.fullName);
        Assert.assertNull(actualOrgView.inn);
        Assert.assertNull(actualOrgView.kpp);
        Assert.assertNull(actualOrgView.phone);
    }

    /**
     * Тест для проверки возврата организации по корректным id
     */
    @Test
    public void getOrganizationByAvailableId() {
        Organization organizationMock = new Organization(1L, "Евросеть", "ПАО Евросеть", "74544", "121", "ул. Пушкина", "8971254545", true);
        Long id = 1L;

        Mockito.when(organizationDao.getOrganizationById(id)).thenReturn(organizationMock);
        OrganizationView actualOrgView = organizationService.getOrganizationById(id);

        Assert.assertNotNull(actualOrgView);
        Assert.assertEquals("1", String.valueOf(actualOrgView.id));
        Assert.assertEquals("Евросеть", actualOrgView.name);
        Assert.assertEquals("ПАО Евросеть", actualOrgView.fullName);
        Assert.assertEquals("74544", actualOrgView.inn);
        Assert.assertEquals("121", actualOrgView.kpp);
        Assert.assertEquals("ул. Пушкина", actualOrgView.address);
        Assert.assertEquals("8971254545", actualOrgView.phone);
        Assert.assertEquals(true, actualOrgView.isActive);
    }

    /**
     * Тест для проверки возврата организации по id, которой нет в базе данных
     */
    @Test(expected = ServiceException.class)
    public void getOrganizationByNotAvailableId() {
        Organization organizationMock = new Organization();
        Mockito.when(organizationDao.getOrganizationById(1L)).thenReturn(organizationMock);
        organizationService.getOrganizationById(1222L);

    }

    /**
     * Тест для проверки обновления организации
     */
    @Test
    public void updateOrganizationWithRequestedFields() {
        ArgumentCaptor<Organization> argument = ArgumentCaptor.forClass(Organization.class);

        OrganizationView organizationViewForUpdate = new OrganizationView(1L, "Мтс", "ПАО МТС", "74544",
                "121", "ул. Пушкина", "8971254545", true);
        Organization organizationMock = new Organization(1L, "Евросеть", "ПАО Евросеть", "74544",
                "121", "ул. Пушкина", "8971254545", true);

        Mockito.when(organizationDao.getOrganizationById(1L)).thenReturn(organizationMock);
        organizationService.updateOrganization(organizationViewForUpdate);
        Mockito.verify(organizationDao).update(argument.capture());
        Organization organizationFromArg = argument.getValue();

        Assert.assertEquals("Мтс", organizationFromArg.getName());
        Assert.assertEquals("ПАО МТС", organizationFromArg.getFullName());
        Assert.assertEquals("74544", organizationFromArg.getInn());
        Assert.assertEquals("121", organizationFromArg.getKpp());
        Assert.assertEquals("ул. Пушкина", organizationFromArg.getAddress());
        Assert.assertEquals("8971254545", organizationFromArg.getPhone());
        Assert.assertEquals(true, organizationFromArg.getIsActive());
    }

    /**
     * Тест для проверки обновления организации c частью запрашиваемых полей
     */
    @Test(expected = ServiceException.class)
    public void updateOrganizationWithPartOfRequestedFields() {
        Organization organizationMock = new Organization();
        Mockito.when(organizationDao.getOrganizationById(1L)).thenReturn(organizationMock);
        OrganizationView organizationViewForUpdateWithException = new OrganizationView();
        organizationViewForUpdateWithException.id = 1L;
        organizationService.updateOrganization(organizationViewForUpdateWithException);
    }

    /**
     * Тест для проверки сохранения организации
     */
    @Test
    public void saveOrganization() {
        ArgumentCaptor<Organization> argument = ArgumentCaptor.forClass(Organization.class);

        OrganizationView organizationView = createOrganizationViewForSave();

        organizationService.saveOrganization(organizationView);
        Mockito.verify(organizationDao).save(argument.capture());
        Assert.assertEquals("МТС", argument.getValue().getName());
    }

    private OrganizationView createOrganizationViewForSave() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.name = "МТС";
        organizationView.fullName = "ПАО МТС";
        organizationView.inn = "1561";
        organizationView.kpp = "4645646";
        organizationView.address = "4645646";
        return organizationView;
    }

    private OrganizationView createOrganizationView() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.name = "Евросеть";
        return organizationView;
    }

    private List<Organization> createList() {
        List<Organization> organizations = new ArrayList<>();
        Organization organizationFromDao = new Organization(1L, "Евросеть", "ПАО Евросеть", "74544", "121", "ул. Пушкина", "8971254545", true);
        organizations.add(organizationFromDao);
        return organizations;
    }
}