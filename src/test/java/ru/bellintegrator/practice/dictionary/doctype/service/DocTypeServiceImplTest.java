package ru.bellintegrator.practice.dictionary.doctype.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.dictionary.doctype.dao.DocTypeDao;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;
import ru.bellintegrator.practice.dictionary.doctype.view.DocTypeView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Тест для проверки сервиса
 */
@RunWith(MockitoJUnitRunner.class)
public class DocTypeServiceImplTest {

    @Mock
    DocTypeDao docTypeDao;

    @InjectMocks
    DocTypeServiceImpl docTypeService;

    /**
     * Тест для проверки возвращения всех типов документов
     */
    @Test
    public void getDocTypes() {
        List<DocType> docTypeListMock = createList();

        Mockito.when(docTypeDao.getAllDocTypes()).thenReturn(docTypeListMock);
        List<DocTypeView> actualList = docTypeService.getDocTypes();

        Assert.assertEquals(2, actualList.size());
        Assert.assertEquals("07", actualList.get(0).code);
        Assert.assertEquals("Паспорт РФ", actualList.get(0).name);
    }

    private List<DocType> createList() {
        List<DocType> docTypeList = new ArrayList<>();
        docTypeList.add(new DocType("Паспорт РФ", "07"));
        docTypeList.add(new DocType("Свидетельство о рождении", "13"));
        return docTypeList;
    }
}