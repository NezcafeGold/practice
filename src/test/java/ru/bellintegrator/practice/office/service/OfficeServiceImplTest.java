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
     * Тест для проверки фильтра офиса c получением списка офисов
     */
    @Test
    public void filterOfficeForList() {
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

    /**
     * Тест для проверки возвращения офиса по существующему id
     */
    @Test
    public void getOfficeByIdWithAvailableId() {
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

    }

    /**
     * Тест для проверки возвращения офиса по некорректному id
     */
    @Test(expected = ServiceException.class)
    public void getOfficeByIdWithNotAvailableId() {
        Office officeMock = new Office();
        Mockito.when(officeDaoMock.getOfficeById(1L)).thenReturn(officeMock);
        OfficeView actualOfficeView = officeService.getOfficeById(1L);
        officeService.getOfficeById(1222L);
    }

    /**
     * Тест для проверки обновления офиса c требуемыми полями
     */
    @Test
    public void updateOfficeWithRequestedFields() {
        ArgumentCaptor<Office> argument = ArgumentCaptor.forClass(Office.class);
        OfficeView officeViewForUpdate = new OfficeView(1L, "Восточный", "ул. Пушкина, 49", "89275588796", true);
        Office officeMock = new Office("Центральный", "ул. Пушкина, 49", "89275588796", true);

        Mockito.when(officeDaoMock.getOfficeById(1L)).thenReturn(officeMock);
        officeService.updateOffice(officeViewForUpdate);
        Mockito.verify(officeDaoMock).update(argument.capture());
        Office officeFromArg = argument.getValue();

        Assert.assertEquals("Восточный", officeFromArg.getName());
        Assert.assertEquals("ул. Пушкина, 49", officeFromArg.getAddress());

    }

    /**
     * Тест для проверки обновления офиса c недостающими данными
     */
    @Test(expected = ServiceException.class)
    public void updateOfficeWithNoRequestedFields() {
        Office officeMock = new Office("Центральный", "ул. Пушкина, 49", "89275588796", true);
        Mockito.when(officeDaoMock.getOfficeById(1L)).thenReturn(officeMock);
        OfficeView officeViewForUpdateWithException = new OfficeView();
        officeViewForUpdateWithException.id = 1L;
        officeService.updateOffice(officeViewForUpdateWithException);
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

    private List<Office> createListMock() {
        List<Office> officeListMock = new ArrayList<>();
        Office office = new Office(1L, "Центральный", "ул. Пушкина, 49", "89275588796", true);
        Office office2 = new Office(2L, "Восточный", "ул. Колотушкина, 29", "89275588796", true);
        officeListMock.add(office);
        officeListMock.add(office2);
        return officeListMock;
    }
}