package ru.bellintegrator.practice.office.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;


/**
 * Тест для проверки ДАО офиса
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "src/main/resources")
@Transactional
public class OfficeDaoImplTest {

    @Autowired
    OfficeDao officeDao;

    /**
     * Тест для проверки фильтра офисов с одним параметром
     */
    @Test
    public void filterOfficeForResultList() {
        List<Office> offices;
        OfficeView officeViewId = new OfficeView();
        officeViewId.orgId = 2L;
        int expectedOffices = 3;
        offices = officeDao.list(officeViewId);
        Assert.assertNotNull(offices);
        Assert.assertEquals(expectedOffices, offices.size());
    }

    /**
     * Тест для проверки фильтра офисов с полным
     */
    @Test
    public void filterOfficeForOneOffice() {
        OfficeView officeViewFull = new OfficeView();
        officeViewFull.orgId = 2L;
        officeViewFull.name = "Рынок";
        officeViewFull.phone = "89179179171";
        officeViewFull.isActive = true;
        List<Office> offices = officeDao.list(officeViewFull);
        Assert.assertNotNull(offices);
        Assert.assertEquals(1, offices.size());
    }

    /**
     * Тест для проверки фильтра офисов с данными, которых нет в базе данных
     */
    @Test
    public void filterOfficeWithNotAvailableValues() {
        OfficeView officeViewWrong = new OfficeView();
        officeViewWrong.orgId = 2L;
        officeViewWrong.name = "Ры";
        officeViewWrong.phone = "8111";
        officeViewWrong.isActive = true;
        List<Office> offices = officeDao.list(officeViewWrong);
        Assert.assertEquals(0, offices.size());
    }

    /**
     * Тест для возвращения офиса по id
     */
    @Test
    public void getOfficeById() {
        Long id = 2L;
        Office office = officeDao.getOfficeById(id);
        String expectedName = "Западный";
        Assert.assertNotNull(office);
        Assert.assertEquals(expectedName, office.getName());
    }

    /**
     * Тест для проверки обновления офиса c требуемыми полями
     */
    @Test
    public void updateWithRequestedFields() {
        Long id = 1L;
        Office expectedOffice = officeDao.getOfficeById(id);
        expectedOffice.setName("Офис");
        expectedOffice.setAddress("Улица");
        officeDao.update(expectedOffice);

        Office actualOffice = officeDao.getOfficeById(id);
        Assert.assertEquals(expectedOffice, actualOffice);
    }

    /**
     * Тест для проверки сохранения офиса
     */
    @Test
    public void save() {
        Office office = new Office("Имя", "Адрес", "5555", true);
        officeDao.save(office);
        Office actualOffice = officeDao.getOfficeById(8L);
        Assert.assertNotNull(actualOffice);
        Assert.assertEquals(office.getName(), actualOffice.getName());
    }
}