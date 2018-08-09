package ru.bellintegrator.practice.user.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.user.model.Document;
import ru.bellintegrator.practice.user.model.User;

/**
 * Тест для проверки ДАО документа
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "src/main/resources")
@Transactional
public class DocumentDaoImplTest {

    @Autowired
    DocumentDao documentDao;

    @Autowired
    UserDao userDao;

    /**
     * Тест для проверки сохранения документа
     */
    @Test
    public void saveDocument() {
        Document document = new Document();
        String firstName = "Геннадий";
        String position = "Кассир";
        document.setDocNumber("4114");
        document.setDocDate("12.05.99");
        User user = new User();
        user.setFirstName(firstName);
        user.setPosition(position);
        user.setDocument(document);
        documentDao.saveDocument(document);
        userDao.saveUser(user);

        User actualUser = userDao.getUserByName(firstName);
        Document actualDocument = actualUser.getDocument();
        Assert.assertEquals(document, actualDocument);
    }

    /**
     * Тест для проверки обновления документа
     */
    @Test
    public void updateDocument() {
        Long id = 1L;
        User user = userDao.getUserById(id);
        Document document = user.getDocument();
        document.setDocNumber("242222");
        document.setDocDate("15.10.15");
        documentDao.updateDocument(document);

        User actualUser = userDao.getUserById(id);
        Document actualDocument = actualUser.getDocument();
        Assert.assertEquals(document, actualDocument);
    }
}