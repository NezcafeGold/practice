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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.ArrayList;
import java.util.List;


/**
 * Тест для проверика сервиса
 */
@RunWith(MockitoJUnitRunner.class)
public class OfficeServiceImplTest {

    @Mock
    OfficeDao officeDaoMock;

    @InjectMocks
    OfficeServiceImpl officeService;

    /**
     * Тест для проверки фильтра офиса
     */
    @Test
    public void filterOffice() {

        List<Office> officeListMock = createListMock();
        OfficeView officeViewForFilter = new OfficeView();
        officeViewForFilter.orgId = 1L;

        Mockito.when(officeDaoMock.list(officeViewForFilter)).thenReturn(officeListMock);

        List<OfficeView> actualOfficeViewList = officeService.filterOffice(officeViewForFilter);

        Assert.assertEquals(2, actualOfficeViewList.size());
        for (OfficeView eachOfficeView : actualOfficeViewList) {
            Assert.assertNotNull(eachOfficeView.id);
            Assert.assertNotNull(eachOfficeView.name);
            Assert.assertNotNull(eachOfficeView.isActive);
            Assert.assertNull(eachOfficeView.phone);
            Assert.assertNull(eachOfficeView.orgId);
        }

    }

    private List<Office> createListMock() {
        List<Office> officeListMock = new ArrayList<>();
        Office office = new Office(1L, "Центральный", "ул. Пушкина, 49", "89275588796", true);
        Office office2 = new Office(2L, "Восточный", "ул. Колотушкина, 29", "89275588796", true);
        officeListMock.add(office);
        officeListMock.add(office2);
        return officeListMock;
    }

    /**
     * Тест для проверки возвращения офиса по id
     */
    @Test
    public void getOfficeById() {
        Office officeMock = new Office(1L, "Центральный", "ул. Пушкина, 49", "89275588796", true);

        Mockito.when(officeDaoMock.getOfficeById(1L)).thenReturn(officeMock);
        OfficeView actualOfficeView = officeService.getOfficeById(1L);

        Assert.assertNotNull(actualOfficeView);
        Assert.assertEquals("1", String.valueOf(actualOfficeView.id));
        Assert.assertEquals("Центральный", actualOfficeView.name);
        Assert.assertEquals("ул. Пушкина, 49", actualOfficeView.address);
        Assert.assertEquals("89275588796", actualOfficeView.phone);
        Assert.assertEquals(true, actualOfficeView.isActive);
        Assert.assertNull(actualOfficeView.orgId);

        try {
            officeService.getOfficeById(1222L);
        } catch (ServiceException e){
            Assert.assertTrue( e.getMessage().equals("Офис 1222 не найден"));
        }
    }

    /**
     * Тест для проверки обновления офиса
     */
    @Test
    public void updateOffice() {
        ArgumentCaptor<Office> argument = ArgumentCaptor.forClass(Office.class);

        OfficeView officeViewForUpdate = new OfficeView(1L, "Восточный", "ул. Пушкина, 49", "89275588796", true);

        OfficeView officeViewForUpdateWithException = new OfficeView();
        officeViewForUpdateWithException.id = 1L;

        Office officeMock = new Office("Центральный", "ул. Пушкина, 49", "89275588796", true);

        Mockito.when(officeDaoMock.getOfficeById(1L)).thenReturn(officeMock);

        officeService.updateOffice(officeViewForUpdate);
        Mockito.verify(officeDaoMock).update(argument.capture());
        Office officeFromArg = argument.getValue();

        Assert.assertEquals("Восточный", officeFromArg.getName());
        Assert.assertEquals("ул. Пушкина, 49", officeFromArg.getAddress());
        try {
            officeService.updateOffice(officeViewForUpdateWithException);
        } catch (ServiceException e){
            Assert.assertTrue( e.getMessage().equals("Не введен обязательный параметр name"));
        }
    }

    /**
     * Тест для проверки сохранения офиса
     */
    @Test
    public void saveOffice() {
        ArgumentCaptor<Office> argument = ArgumentCaptor.forClass(Office.class);

        OfficeView officeViewForSave = new OfficeView();
        officeViewForSave.name = "Восточный";

        officeService.saveOffice(officeViewForSave);
        Mockito.verify(officeDaoMock).save(argument.capture());
        Assert.assertEquals("Восточный", argument.getValue().getName());
    }
}