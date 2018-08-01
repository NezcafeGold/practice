package ru.bellintegrator.practice.office.dao;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
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


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "src/main/resources")
@Transactional
public class OfficeDaoImplTest {

    @Autowired
    OfficeDao officeDao;

    @Test
    public void list() {
        List<Office> offices;
        OfficeView officeViewId = new OfficeView();
        officeViewId.orgId = 2L;
        int expectedOffices = 3;
        offices = officeDao.list(officeViewId);
        Assert.assertNotNull(offices);
        Assert.assertEquals(expectedOffices, offices.size());

        OfficeView officeViewFull = new OfficeView();
        officeViewFull.orgId = 2L;
        officeViewFull.name = "Рынок";
        officeViewFull.phone = "89179179171";
        officeViewFull.isActive = true;
        offices = officeDao.list(officeViewFull);
        Assert.assertNotNull(offices);
        Assert.assertEquals(1, offices.size());

        OfficeView officeViewWrong = new OfficeView();
        officeViewFull.orgId = 2L;
        officeViewFull.name = "Ры";
        officeViewFull.phone = "8111";
        officeViewFull.isActive = true;
        offices = officeDao.list(officeViewWrong);
        Assert.assertEquals(0, offices.size());
    }

    @Test
    public void getOfficeById() {
        Long id = 2L;
        Office office = officeDao.getOfficeById(id);
        String expectedName = "Западный";
        Assert.assertNotNull(office);
        Assert.assertEquals(expectedName, office.getName());
    }

    @Test
    public void update() {
        Long id = 1L;
        Office expectedOffice = officeDao.getOfficeById(id);
        expectedOffice.setName("Офис");
        expectedOffice.setAddress("Улица");
        officeDao.update(expectedOffice);

        Office actualOffice = officeDao.getOfficeById(id);
        Assert.assertEquals(expectedOffice, actualOffice);
    }

    @Test
    public void save() {
        Office office = new Office("Имя", "Адрес", "5555", true);
        officeDao.save(office);
        Office actualOffice = officeDao.getOfficeById(8L);
        Assert.assertNotNull(actualOffice);
        Assert.assertEquals(office.getName(), actualOffice.getName());
    }
}