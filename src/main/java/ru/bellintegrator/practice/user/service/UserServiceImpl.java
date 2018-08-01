package ru.bellintegrator.practice.user.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final DocTypeDao docTypeDao;
    private final CountryDao countryDao;
    private final DocumentDao documentDao;
    private MapperFactory mapperFactory;

    @Autowired
    public UserServiceImpl(UserDao userDao, DocTypeDao docTypeDao, CountryDao countryDao, DocumentDao documentDao) {
        this.userDao = userDao;
        this.docTypeDao = docTypeDao;
        this.countryDao = countryDao;
        this.documentDao = documentDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserView> filterUser(UserView userView) {
        if (userView.officeId == null) {
            throw new ServiceException("Не введен обязательный параметр officeId");
        }
        List<User> users = userDao.filterUser(userView);
        List<UserView> filterList = new ArrayList<>();
        mapperFactory = new DefaultMapperFactory.Builder().build();
        for (int i = 0; i < users.size(); i++) {
            UserView userFilterView = new UserView();
            mapperFactory.classMap(User.class, UserView.class)
                    .field("id", "id")
                    .field("firstName", "firstName")
                    .field("secondName", "secondName")
                    .field("middleName", "middleName")
                    .field("position", "position").register();
            MapperFacade mapper = mapperFactory.getMapperFacade();
            mapper.map(users.get(i), userFilterView);
            filterList.add(userFilterView);
        }
        return filterList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserView getUserById(Long id) {
        User user = userDao.getUserById(id);
        Document document = user.getDocument();

        DocType docType = document.getDocType();
        Country country = document.getCountry();

        if (user == null) {
            throw new ServiceException("Пользователь " + id + " не найден");
        }
        if (document == null) {
            throw new ServiceException("Документ " + id + " не найден");
        }
        UserView userView = new UserView();
        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(User.class, UserView.class)
                .mapNulls(false)
                .field("id", "id")
                .field("firstName", "firstName")
                .field("secondName", "secondName")
                .field("middleName", "middleName")
                .field("position", "position")
                .field("phone", "phone")
                .field("isIdentified", "isIdentified")
                .byDefault()
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(user, userView);
        if (userView.id == null) {
            userView.id = user.getId();
        }

        if (docType != null) {
            mapperFactory = new DefaultMapperFactory.Builder().build();
            mapperFactory.classMap(DocType.class, UserView.class)
                    .field("name", "docName").register();
            mapper = mapperFactory.getMapperFacade();
            mapper.map(docType, userView);
        }
        if (country != null) {
            mapperFactory = new DefaultMapperFactory.Builder().build();
            mapperFactory.classMap(Country.class, UserView.class)
                    .field("name", "citizenshipName")
                    .field("code", "citizenshipCode")
                    .register();
            mapper = mapperFactory.getMapperFacade();
            mapper.map(country, userView);
        }
        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Document.class, UserView.class)
                .field("docNumber", "docNumber")
                .field("docDate", "docDate")
                .register();
        mapper = mapperFactory.getMapperFacade();
        mapper.map(document, userView);

        return userView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateUser(UserView userView) {
        if (userView.id == null) {
            throw new ServiceException("Не введен обязательный параметр id");
        }
        if (userView.firstName == null) {
            throw new ServiceException("Не введен обязательный параметр firstName");
        }
        if (userView.position == null) {
            throw new ServiceException("Не введен обязательный параметр position");
        }
        Long id = userView.id;
        User user = userDao.getUserById(id);
        if (user == null) {
            throw new ServiceException("Пользователь " + id + " не найден");
        }

        DocType docType = null;
        String docName = userView.docName;
        if (docName != null) {
            docType = docTypeDao.getDocTypeByName(docName);
        }

        Country country = null;
        String citizenshipCode = userView.citizenshipCode;
        if (citizenshipCode != null) {
            country = countryDao.getCountryByCode(citizenshipCode);
        }

        Document document = user.getDocument();
        if (document == null) {
            throw new ServiceException("Документ " + id + " не найден");
        }

        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(UserView.class, User.class)
                .mapNulls(false)
                .field("firstName", "firstName")
                .field("secondName", "secondName")
                .field("middleName", "middleName")
                .field("position", "position")
                .field("phone", "phone")
                .field("isIdentified", "isIdentified")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(userView, user);

        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(UserView.class, Document.class)
                .mapNulls(false)
                .field("docNumber", "docNumber")
                .field("docDate", "docDate")
                .register();
        mapper = mapperFactory.getMapperFacade();
        mapper.map(userView, document);

        user.setDocument(document);
        if (docType != null) {
            document.setDocType(docType);
        }
        if (country != null) {
            document.setCountry(country);
        }
        userDao.updateUser(user);
        documentDao.updateDocument(document);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveUser(UserView userView) {
        User user = new User();
        if (userView.firstName == null) {
            throw new ServiceException("Не введен обязательный параметр firstName");
        }
        if (userView.position == null) {
            throw new ServiceException("Не введен обязательный параметр position");
        }

        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(UserView.class, User.class)
                .mapNulls(false)
                .field("firstName", "firstName")
                .field("position", "position")
                .mapNulls(true)
                .field("secondName", "secondName")
                .field("middleName", "middleName")
                .field("phone", "phone")
                .field("isIdentified", "isIdentified")
                .byDefault()
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(userView, user);

        DocType docType = null;
        String docName = userView.docName;
        if (docName != null) {
            docType = docTypeDao.getDocTypeByName(docName);
        }

        Country country = null;
        String citizenshipCode = userView.citizenshipCode;
        if (citizenshipCode != null) {
            country = countryDao.getCountryByCode(citizenshipCode);
        }

        String docNumber = userView.docNumber;
        String docDate = userView.docDate;
        Document document = new Document();
        if (docNumber != null || docDate != null) {
            mapperFactory = new DefaultMapperFactory.Builder().build();
            mapperFactory.classMap(UserView.class, Document.class)
                    .field("docNumber", "docNumber")
                    .field("docDate", "docDate")
                    .register();
            mapper = mapperFactory.getMapperFacade();
            mapper.map(userView, document);
        }

        if (country != null) {
            document.setCountry(country);
        }
        if (docType != null) {
            document.setDocType(docType);
        }

        user.setDocument(document);

        documentDao.saveDocument(document);
        userDao.saveUser(user);
    }
}
