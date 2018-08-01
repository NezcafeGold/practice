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

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
public class DocTypeServiceImplTest {

    @Mock
    DocTypeDao docTypeDao;

    @InjectMocks
    DocTypeServiceImpl docTypeService;

    @Test
    public void getDocTypes() {
        ArgumentCaptor<DocType> argument = ArgumentCaptor.forClass(DocType.class);
        List<DocType> docTypeList = new ArrayList<>();
        DocType docType = new DocType("Паспорт РФ", "07");
        DocType docType1 = new DocType("Свидетельство о рождении", "13");
        docTypeList.add(docType);
        docTypeList.add(docType1);

        Mockito.when(docTypeDao.getAllDocTypes()).thenReturn(docTypeList);
        List<DocTypeView> actualList = docTypeService.getDocTypes();
        Assert.assertEquals(2, actualList.size());
        Assert.assertEquals("07", actualList.get(0).code);
        Assert.assertEquals("Паспорт РФ", actualList.get(0).name);
    }
}