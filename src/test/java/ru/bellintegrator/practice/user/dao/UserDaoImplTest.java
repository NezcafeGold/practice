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
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "src/main/resources")
@Transactional
public class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Autowired
    DocumentDao documentDao;

    @Test
    public void filterUser() {
        List<User> userList;
        UserView userView = new UserView();
        userView.officeId = 4L;
        int expectedUsers = 3;
        userList = userDao.filterUser(userView);
        Assert.assertNotNull(userList);
        Assert.assertEquals(expectedUsers, userList.size());

        UserView userViewFull = new UserView();
        userViewFull.officeId = 4L;
        userViewFull.firstName = "Вячеслав";
        userViewFull.secondName ="Андреев";
        userViewFull.docCode ="21";
        userList = userDao.filterUser(userViewFull);
        expectedUsers = 1;
        Assert.assertNotNull(userList);
        Assert.assertEquals(expectedUsers, userList.size());

        UserView userViewWrong = new UserView();
        userViewFull.officeId = 4L;
        userViewFull.firstName = "Ры";
        expectedUsers = 0;
        userList = userDao.filterUser(userViewWrong);
        Assert.assertEquals(expectedUsers, userList.size());
    }

    @Test
    public void updateUser() {
        Long id = 1L;
        User user = userDao.getUserById(id);
        user.setPosition("Директоррр");
        user.setFirstName("Дмитрий");
        userDao.updateUser(user);

        User actualUser = userDao.getUserById(id);
        Assert.assertEquals(user, actualUser);
    }

    @Test
    public void saveUser() {
        String firstName = "Дмитрий";
        String position = "Тестировщик";
        Document document = new Document();
        User user = new User();
        user.setPosition(position);
        user.setFirstName(firstName);
        user.setDocument(document);

        documentDao.saveDocument(document);
        userDao.saveUser(user);

        User actualUser = userDao.getUserByName(firstName);
        Assert.assertEquals(user, actualUser);
    }

    @Test
    public void getUserById() {
        Long id = 1L;
        User user = userDao.getUserById(id);
        String firstName = "Михаил";

        Assert.assertNotNull(user);
        Assert.assertEquals(firstName, user.getFirstName());
    }
}