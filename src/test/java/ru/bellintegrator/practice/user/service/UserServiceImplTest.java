package ru.bellintegrator.practice.user.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;
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

/**
 * Тест для проверки сервиса пользователя
 */
@RunWith(MockitoJUnitRunner.class)
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

    /**
     * Тест для проверки фильтра пользователя
     */
    @Test
    public void filterUser() {
        UserView userViewForFilter = createUserView();
        List<User> userListMock = createUserList();
        Mockito.when(userDao.filterUser(userViewForFilter)).thenReturn(userListMock);

        List<UserView> actualUserViewList = userService.filterUser(userViewForFilter);
        UserView actualUserView = actualUserViewList.get(0);
        Assert.assertNotNull(actualUserViewList);
        Assert.assertEquals(2, actualUserViewList.size());
        Assert.assertEquals("5", String.valueOf(actualUserView.id));
        Assert.assertEquals("Вячеслав", actualUserView.firstName);
        Assert.assertEquals("Андреев", actualUserView.secondName);
        Assert.assertEquals("Романович", actualUserView.middleName);
        Assert.assertEquals("Продавец", actualUserView.position);
        Assert.assertNull(actualUserView.phone);
        Assert.assertNull(actualUserView.isIdentified);
    }

    /**
     * Тест для проверки фильтра пользователя c пустыми полями
     */
    @Test(expected = ServiceException.class)
    public void filterUserWithNoRequestedFields() {
        UserView userViewForFilter = createUserView();
        List<User> userListMock = createUserList();
        Mockito.when(userDao.filterUser(userViewForFilter)).thenReturn(userListMock);
        List<UserView> actualUserViewList = userService.filterUser(userViewForFilter);
        UserView userViewForFilterWithException = new UserView();
        userService.filterUser(userViewForFilterWithException);
    }

    /**
     * Тест для проверки возвращения пользователя по id, который есть в базе данных
     */
    @Test
    public void getUserByAvailableId() {
        User userMock = createUser();

        Mockito.when(userDao.getUserById(1L)).thenReturn(userMock);
        Mockito.when(userDao.getUserById(10L)).thenReturn(null);
        UserView actualUserView = userService.getUserById(1L);
        Assert.assertNotNull(actualUserView);
        Assert.assertEquals("1", String.valueOf(actualUserView.id));
        Assert.assertEquals("Вячеслав", actualUserView.firstName);
        Assert.assertEquals("Андреев", actualUserView.secondName);
        Assert.assertEquals("Романович", actualUserView.middleName);
        Assert.assertEquals("Продавец", actualUserView.position);
        Assert.assertEquals("89285585484", actualUserView.phone);
        Assert.assertEquals("Военный билет", actualUserView.docName);
        Assert.assertEquals("454454", actualUserView.docNumber);
        Assert.assertEquals("25.01.17", actualUserView.docDate);
        Assert.assertEquals("Украина", actualUserView.citizenshipName);
        Assert.assertEquals("804", actualUserView.citizenshipCode);
        Assert.assertEquals(true, actualUserView.isIdentified);
    }

    /**
     * Тест для проверки возвращения пользователя по id, которого нет в базе данных
     */
    @Test(expected = ServiceException.class)
    public void getUserByNotAvailableId() {
        Mockito.when(userDao.getUserById(10L)).thenReturn(null);
        userService.getUserById(10L);
    }

    /**
     * Тест для проверки обновления пользователя
     */
    @Test
    public void updateUserWithRequestedFields() {
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        UserView userViewForUpdate = new UserView(1L, "Михаил", "Боров", "Андреевич", "Директор", "89285585484",
                "21", "Паспорт гражданина Российской Федерации", "454454", "25.01.17", "804", true);
        DocType docTypeMock = new DocType("Паспорт гражданина Российской Федерации", "21");
        Country countryMock = new Country("Украина", "804");


        User user = new User(1L, "Вячеслав", "Андреев", "Романович", "Продавец", "89285585484", true);
        DocType docType = new DocType("Военный билет", "07");
        Country country = new Country("Украина", "804");
        Document document = new Document("454454", "25.01.17", docType, country);
        user.setDocument(document);

        Mockito.when(userDao.getUserById(userViewForUpdate.id)).thenReturn(user);
        Mockito.when(docTypeDao.getDocTypeByName(userViewForUpdate.docName)).thenReturn(docTypeMock);
        Mockito.when(countryDao.getCountryByCode(userViewForUpdate.citizenshipCode)).thenReturn(countryMock);
        userService.updateUser(userViewForUpdate);
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

    /**
     * Тест для проверки обновления пользователя c отсутсвующими полями
     */
    @Test(expected = ServiceException.class)
    public void updateUserWithPartOfRequestedFields() {
        UserView userView = new UserView();
        userService.updateUser(userView);
    }

    /**
     * Тест для проверки сохранения пользователя
     */
    @Test
    public void saveUser() {
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);

        UserView userViewForSave = new UserView(1L, "Михаил", "Боров", "Андреевич", "Директор", "89285585484",
                "21", "Паспорт гражданина Российской Федерации", "454454", "25.01.17", "804", true);
        DocType docTypeMock = new DocType("Паспорт гражданина Российской Федерации", "21");
        Country countryInMock = new Country("Украина", "804");

        Mockito.when(docTypeDao.getDocTypeByName(userViewForSave.docName)).thenReturn(docTypeMock);
        Mockito.when(countryDao.getCountryByCode(userViewForSave.citizenshipCode)).thenReturn(countryInMock);
        userService.saveUser(userViewForSave);
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

    private UserView createUserView() {
        UserView userViewForFilter = new UserView();
        userViewForFilter.officeId = 4L;
        return userViewForFilter;
    }

    private List<User> createUserList() {
        List<User> userList = new ArrayList<>();
        User user = new User(5L, "Вячеслав", "Андреев", "Романович", "Продавец", "89285585484", true);
        User user2 = new User(6L, "Марат", "Маратов", "Маратович", "Охранник", "8915125484", true);
        userList.add(user);
        userList.add(user2);
        return userList;
    }

    private User createUser() {
        User user = new User(1L, "Вячеслав", "Андреев", "Романович", "Продавец", "89285585484", true);
        DocType docType = new DocType("Военный билет", "07");
        Country country = new Country("Украина", "804");
        Document document = new Document("454454", "25.01.17", docType, country);
        user.setDocument(document);
        return user;
    }
}