package ru.bellintegrator.practice.user.service;

import ma.glasnost.orika.BoundMapperFacade;
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
import ru.bellintegrator.practice.user.view.UserFilterView;
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
    @Transactional
    public List<UserFilterView> filterUser(UserView userView) {
        List<User> users = userDao.filterUser(userView);
        List<UserFilterView> filterList = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            UserFilterView userFilterView = new UserFilterView();
            userFilterView.id = users.get(i).getId();
            userFilterView.firstName = users.get(i).getFirstName();
            userFilterView.secondName = users.get(i).getSecondName();
            userFilterView.middleName = users.get(i).getMiddleName();
            userFilterView.position = users.get(i).getPosition();
            filterList.add(userFilterView);
        }
        return filterList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public UserView getUserById(Long id) {
        User user = userDao.getUserById(id);
        Document document = documentDao.getDocumentById(id);
        DocType docType = documentDao.getDocTypeById(id);
        Country country = documentDao.getCountryById(id);
        if (user == null) {
            throw new ServiceException("Пользователь " + id + " не найден");
        }
        if (document == null) {
            throw new ServiceException("Документ " + id + " не найден");
        }
        if (docType == null) {
            throw new ServiceException("Тип документа " + id + " не найден");
        }
        if (country == null) {
            throw new ServiceException("Тип страны " + id + " не найден");
        }
        UserView userView = new UserView();
        userView.id = user.getId();
        userView.firstName = user.getFirstName();
        userView.secondName = user.getSecondName();
        userView.middleName = user.getMiddleName();
        userView.position = user.getPosition();
        userView.phone = user.getPhone();
        userView.docName = docType.getName();
        userView.docNumber = document.getDocNumber();
        userView.docDate = document.getDocDate();
        userView.citizenshipName = country.getName();
        userView.citizenshipCode = country.getCode();
        userView.isIdentified = user.getIdentified();
        return userView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateUser(UserView userView) {
        Long id = userView.id;
        User user = userDao.getUserById(id);
        if (user == null) {
            throw new ServiceException("Пользователь " + id + " не найден");
        }

        DocType docType = null;
        String docName = userView.docName;
        if (docName != null) {
            List<DocType> docTypes = docTypeDao.getAllDocTypes();
            for (DocType dt : docTypes) {
                if (dt.getName().equals(docName)) {
                    docType = dt;
                }
            }
        }

        Country country = null;
        String citizenshipCode = userView.citizenshipCode;
        if (citizenshipCode != null) {
            List<Country> countries = countryDao.getAllCountries();
            for (Country c : countries) {
                if (c.getCode().equals(citizenshipCode)) {
                    country = c;
                }
            }
        }

        Document document = documentDao.getDocumentById(id);
        if (document == null) {
            throw new ServiceException("Документ " + id + " не найден");
        }

        user.setFirstName(userView.firstName);
        user.setSecondName(userView.secondName);
        user.setMiddleName(userView.middleName);
        user.setPhone(userView.phone);
        user.setPosition(userView.position);
        user.setIdentified(userView.isIdentified);
        document.setDocNumber(userView.docNumber);
        document.setDocDate(userView.docDate);
        document.setUser(user);
        document.setDocType(docType);
        document.setCountry(country);

        userDao.updateUser(user);
        documentDao.updateDocument(document);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveUser(UserView userView) {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        BoundMapperFacade boundMapper = mapperFactory.getMapperFacade(UserView.class, User.class);
        User user = (User) boundMapper.map(userView);

        DocType docType = null;
        String docName = userView.docName;
        if (docName != null) {
            List<DocType> docTypes = docTypeDao.getAllDocTypes();
            for (DocType dt : docTypes) {
                if (dt.getName().equals(docName)) {
                    docType = dt;
                }
            }
        }

        Country country = null;
        String citizenshipCode = userView.citizenshipCode;
        if (citizenshipCode != null) {
            List<Country> countries = countryDao.getAllCountries();
            for (Country c : countries) {
                if (c.getCode().equals(citizenshipCode)) {
                    country = c;
                }
            }
        }

        Document document = new Document(userView.docNumber, userView.docDate, user, docType, country);

        userDao.saveUser(user);
        documentDao.saveDocument(document);
    }
}
