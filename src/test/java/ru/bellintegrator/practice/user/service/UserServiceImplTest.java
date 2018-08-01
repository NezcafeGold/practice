package ru.bellintegrator.practice.user.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
import ru.bellintegrator.practice.dictionary.country.dao.CountryDao;
import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.doctype.dao.DocTypeDao;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;
import ru.bellintegrator.practice.user.dao.DocumentDao;
import ru.bellintegrator.practice.user.dao.UserDao;
import ru.bellintegrator.practice.user.model.Document;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "src/main/resources")
@Transactional
public class UserServiceImplTest {

    @Mock
    UserDao userDao;

    @Mock
    DocTypeDao docTypeDao;

    @Mock
    DocumentDao documentDao;

    @Mock
    CountryDao countryDao;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void filterUser() {
        UserView userView = new UserView();
        userView.officeId = 4L;

        List<User> userList = new ArrayList<>();
        User user = new User(5L, "Вячеслав", "Андреев", "Романович", "Продавец", "89285585484", true);
        User user2 = new User(6L, "Марат", "Маратов", "Маратович", "Охранник", "8915125484", true);
        userList.add(user);
        userList.add(user2);

        Mockito.when(userDao.filterUser(userView)).thenReturn(userList);
        List<UserView> userViewList = userService.filterUser(userView);
        Assert.assertNotNull(userViewList);
        UserView userViewFromList = userViewList.get(0);
        Assert.assertEquals(2, userViewList.size());
        Assert.assertEquals("5", String.valueOf(userViewFromList.id));
        Assert.assertEquals("Вячеслав", userViewFromList.firstName);
        Assert.assertEquals("Андреев", userViewFromList.secondName);
        Assert.assertEquals("Романович", userViewFromList.middleName);
        Assert.assertEquals("Продавец", userViewFromList.position);
        Assert.assertNull(userViewFromList.phone);
        Assert.assertNull(userViewFromList.isIdentified);
    }

    @Test
    public void getUserById() {
        Long id = 1L;

        User user = new User(5L, "Вячеслав", "Андреев", "Романович", "Продавец", "89285585484", true);
        DocType docType = new DocType("Военный билет", "07");
        Country country = new Country("Украина", "804");
        Document document = new Document("454454", "25.01.17", docType, country);
        user.setDocument(document);

        Mockito.when(userDao.getUserById(id)).thenReturn(user);
        UserView userView = userService.getUserById(id);

        Assert.assertNotNull(userView);
        Assert.assertEquals("5", String.valueOf(userView.id));
        Assert.assertEquals("Вячеслав", userView.firstName);
        Assert.assertEquals("Андреев", userView.secondName);
        Assert.assertEquals("Романович", userView.middleName);
        Assert.assertEquals("Продавец", userView.position);
        Assert.assertEquals("89285585484", userView.phone);
        Assert.assertEquals("Военный билет", userView.docName);
        Assert.assertEquals("454454", userView.docNumber);
        Assert.assertEquals("25.01.17", userView.docDate);
        Assert.assertEquals("Украина", userView.citizenshipName);
        Assert.assertEquals("804", userView.citizenshipCode);
        Assert.assertEquals(true, userView.isIdentified);
    }

    @Test
    public void updateUser() {
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        UserView userView = new UserView(1L, "Михаил", "Боров", "Андреевич", "Директор", "89285585484",
                "21", "Паспорт гражданина Российской Федерации", "454454", "25.01.17", "804", true);
        DocType docTypeIn = new DocType("Паспорт гражданина Российской Федерации", "21");
        Country countryIn = new Country("Украина", "804");


        User user = new User(1L, "Вячеслав", "Андреев", "Романович", "Продавец", "89285585484", true);
        DocType docType = new DocType("Военный билет", "07");
        Country country = new Country("Украина", "804");
        Document document = new Document("454454", "25.01.17", docType, country);
        user.setDocument(document);

        Mockito.when(userDao.getUserById(userView.id)).thenReturn(user);
        Mockito.when(docTypeDao.getDocTypeByName(userView.docName)).thenReturn(docTypeIn);
        Mockito.when(countryDao.getCountryByCode(userView.citizenshipCode)).thenReturn(countryIn);
        userService.updateUser(userView);
        Mockito.verify(userDao).updateUser(argument.capture());
        User userFromArg = argument.getValue();
        Document documentFromArg = userFromArg.getDocument();
        DocType docTypeFromArg = documentFromArg.getDocType();
        Country countryFromArg = documentFromArg.getCountry();
        Assert.assertEquals("Михаил", userFromArg.getFirstName());
        Assert.assertEquals("Боров", userFromArg.getSecondName());
        Assert.assertEquals("Андреевич", userFromArg.getMiddleName());
        Assert.assertEquals("Директор", userFromArg.getPosition());
        Assert.assertEquals("89285585484", userFromArg.getPhone());
        Assert.assertEquals("21", docTypeFromArg.getCode());
        Assert.assertEquals("Паспорт гражданина Российской Федерации", docTypeFromArg.getName());
        Assert.assertEquals("454454", documentFromArg.getDocNumber());
        Assert.assertEquals("25.01.17", documentFromArg.getDocDate());
        Assert.assertEquals("804", countryFromArg.getCode());
        Assert.assertEquals(true, userFromArg.getIsIdentified());
    }

    @Test
    public void saveUser() {
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        UserView userView = new UserView(1L, "Михаил", "Боров", "Андреевич", "Директор", "89285585484",
                "21", "Паспорт гражданина Российской Федерации", "454454", "25.01.17", "804", true);
        DocType docTypeIn = new DocType("Паспорт гражданина Российской Федерации", "21");
        Country countryIn = new Country("Украина", "804");

        Mockito.when(docTypeDao.getDocTypeByName(userView.docName)).thenReturn(docTypeIn);
        Mockito.when(countryDao.getCountryByCode(userView.citizenshipCode)).thenReturn(countryIn);
        userService.saveUser(userView);

        Mockito.verify(userDao).saveUser(argument.capture());

        User userFromArg = argument.getValue();
        Document documentFromArg = userFromArg.getDocument();
        DocType docTypeFromArg = documentFromArg.getDocType();
        Country countryFromArg = documentFromArg.getCountry();
        Assert.assertEquals("Михаил", userFromArg.getFirstName());
        Assert.assertEquals("Боров", userFromArg.getSecondName());
        Assert.assertEquals("Андреевич", userFromArg.getMiddleName());
        Assert.assertEquals("Директор", userFromArg.getPosition());
        Assert.assertEquals("89285585484", userFromArg.getPhone());
        Assert.assertEquals("21", docTypeFromArg.getCode());
        Assert.assertEquals("Паспорт гражданина Российской Федерации", docTypeFromArg.getName());
        Assert.assertEquals("454454", documentFromArg.getDocNumber());
        Assert.assertEquals("25.01.17", documentFromArg.getDocDate());
        Assert.assertEquals("804", countryFromArg.getCode());
        Assert.assertEquals(true, userFromArg.getIsIdentified());
    }
}