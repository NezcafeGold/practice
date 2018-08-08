package ru.bellintegrator.practice.dictionary.doctype.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;

import java.util.List;


/**
 * Тест для проверки ДАО
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "src/main/resources")
public class DocTypeDaoImplTest {


    @Autowired
    DocTypeDao docTypeDao;

    /**
     * Тест для проверки возвращения типа документа по имени
     */
    @Test
    public void getDocTypeByName() {
        String name = "Военный билет";
        DocType docType = docTypeDao.getDocTypeByName(name);
        String expectedCode = "07";
        String expectedName = name;

        Assert.assertNotNull(docType);
        Assert.assertEquals(expectedName, docType.getName());
        Assert.assertEquals(expectedCode, docType.getCode());
    }

    /**
     * Тест для проверки возвращения всех типов документов
     */
    @Test
    public void getAllDocTypes() {
        List<DocType> docTypeList = docTypeDao.getAllDocTypes();
        int expectedDocTypes = 6;
        Assert.assertEquals(expectedDocTypes, docTypeList.size());
    }
}