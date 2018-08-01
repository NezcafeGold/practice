package ru.bellintegrator.practice.office.service;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
public class OfficeServiceImplTest {

    @Mock
    OfficeDao officeDaoMock;

    @InjectMocks
    OfficeServiceImpl officeService;

    @Test
    public void filterOffice() {
        List<Office> officeListMock = new ArrayList<>();
        Office office = new Office(1L, "Центральный", "ул. Пушкина, 49", "89275588796", true);
        Office office2 = new Office(2L, "Восточный", "ул. Колотушкина, 29", "89275588796", true);
        officeListMock.add(office);
        officeListMock.add(office2);

        OfficeView officeView = new OfficeView();
        officeView.orgId = 1L;

        Mockito.when(officeDaoMock.list(officeView)).thenReturn(officeListMock);

        int expectedOffices = 2;
        List<OfficeView> actualOfficeViewList = officeService.filterOffice(officeView);
        Assert.assertEquals(expectedOffices, actualOfficeViewList.size());
        for (OfficeView eachOfficeView : actualOfficeViewList) {
            Assert.assertNotNull(eachOfficeView.id);
            Assert.assertNotNull(eachOfficeView.name);
            Assert.assertNotNull(eachOfficeView.isActive);
            Assert.assertNull(eachOfficeView.phone);
            Assert.assertNull(eachOfficeView.orgId);
        }

    }

    @Test
    public void getOfficeById() {
        Office office = new Office(1L, "Центральный", "ул. Пушкина, 49", "89275588796", true);

        Long id = 2L;
        Mockito.when(officeDaoMock.getOfficeById(id)).thenReturn(office);

        Long expectedId = 1L;
        String expectedName = "Центральный";
        String expectedAddress = "ул. Пушкина, 49";
        String expectedPhone = "89275588796";
        Boolean expectedActive = true;

        OfficeView actualOfficeView = officeService.getOfficeById(id);
        Assert.assertNotNull(actualOfficeView);
        Assert.assertEquals(expectedId, actualOfficeView.id);
        Assert.assertEquals(expectedName, actualOfficeView.name);
        Assert.assertEquals(expectedAddress, actualOfficeView.address);
        Assert.assertEquals(expectedPhone, actualOfficeView.phone);
        Assert.assertEquals(expectedActive, actualOfficeView.isActive);
        Assert.assertNull(actualOfficeView.orgId);

        try {
            officeService.getOfficeById(1222L);
        } catch (ServiceException e){
            Assert.assertTrue( e.getMessage().equals("Офис 1222 не найден"));
        }
    }

    @Test
    public void updateOffice() {
        ArgumentCaptor<Office> argument = ArgumentCaptor.forClass(Office.class);
        OfficeView officeView = new OfficeView(1L, "Восточный", "ул. Пушкина, 49", "89275588796", true);
        OfficeView officeView2 = new OfficeView();
        officeView2.id = 1L;

        Office office = new Office("Центральный", "ул. Пушкина, 49", "89275588796", true);

        Mockito.when(officeDaoMock.getOfficeById(1L)).thenReturn(office);

        officeService.updateOffice(officeView);
        Mockito.verify(officeDaoMock).update(argument.capture());
        Assert.assertEquals("Восточный", argument.getValue().getName());
        Assert.assertEquals("ул. Пушкина, 49", argument.getValue().getAddress());
        try {
            officeService.updateOffice(officeView2);
        } catch (ServiceException e){
            Assert.assertTrue( e.getMessage().equals("Не введен обязательный параметр name"));
        }

    }

    @Test
    public void saveOffice() {
        ArgumentCaptor<Office> argument = ArgumentCaptor.forClass(Office.class);
        OfficeView officeView = new OfficeView();
        officeView.name = "Восточный";
        officeService.saveOffice(officeView);
        Mockito.verify(officeDaoMock).save(argument.capture());
        Assert.assertEquals("Восточный", argument.getValue().getName());
    }
}