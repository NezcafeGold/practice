package ru.bellintegrator.practice.organization.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

/**
 * Тест для проверки ДАО
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "src/main/resources")
@Transactional
public class OrganizationDaoImplTest {

    @Autowired
    OrganizationDao organizationDao;

    /**
     * Тест для проверки фильтра организации c полями, которые есть в базе данных
     */
    @Test
    public void filterOrganizationWithAvailableFields() {
        List<Organization> organizations;
        OrganizationView organizationView = new OrganizationView();
        organizationView.name = "Евросеть";
        int expectedOffices = 1;
        organizations = organizationDao.list(organizationView);
        Assert.assertNotNull(organizations);
        Assert.assertEquals(expectedOffices, organizations.size());

        OrganizationView organizationViewTwoFileds = new OrganizationView();
        organizationViewTwoFileds.name = "DNS";
        organizationViewTwoFileds.inn = "566401571";
        expectedOffices = 1;
        organizations = organizationDao.list(organizationViewTwoFileds);
        Assert.assertNotNull(organizations);
        Assert.assertEquals(expectedOffices, organizations.size());
    }

    /**
     * Тест для проверки фильтра организации c полями, которых нет в базе данных
     */
    @Test
    public void filterOrganizationWithNotAvailableFields() {
        OrganizationView organizationView = new OrganizationView();
        organizationView.name = "Какая-то организация";
        organizationView.phone = "8111";
        int expectedOffices = 0;
        List<Organization> organizations = organizationDao.list(organizationView);
        Assert.assertEquals(expectedOffices, organizations.size());
        
    }
    /**
     * Тест для проверки возвращения организации по id
     */
    @Test
    public void getOrganizationById() {
        Long id = 3L;
        Organization organization = organizationDao.getOrganizationById(id);
        String expectedName = "МТС";
        Assert.assertNotNull(organization);
        Assert.assertEquals(expectedName, organization.getName());
    }

    /**
     * Тест для проверки обновления организации
     */
    @Test
    public void update() {
        Long id = 1L;
        Organization expectedOrganization = organizationDao.getOrganizationById(id);
        expectedOrganization.setName("Имя");
        expectedOrganization.setFullName("Полное имя");
        expectedOrganization.setInn("441554");
        expectedOrganization.setKpp("13213");
        expectedOrganization.setAddress("Улица");
        organizationDao.update(expectedOrganization);
        Organization actualOrganization = organizationDao.getOrganizationById(id);
        Assert.assertEquals(expectedOrganization, actualOrganization);
    }

    /**
     * Тест для проверки сохранения организации
     */
    @Test
    public void save() {
        Organization expectedOrganization = new Organization();
        expectedOrganization.setName("Имя");
        expectedOrganization.setFullName("Полное имя");
        expectedOrganization.setInn("441554");
        expectedOrganization.setKpp("13213");
        expectedOrganization.setAddress("Улица");
        organizationDao.save(expectedOrganization);

        Organization actualOrganization = organizationDao.getOrganizationById(4L);
        Assert.assertNotNull(actualOrganization);
        Assert.assertEquals(expectedOrganization, actualOrganization);
    }
}