package ru.bellintegrator.practice.user.service;

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
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, DocTypeDao docTypeDao, CountryDao countryDao, DocumentDao documentDao) {
        this.userDao = userDao;
        this.docTypeDao = docTypeDao;
        this.countryDao = countryDao;
        this.documentDao = documentDao;
        userMapper = new UserMapper();
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
        List<UserView> filterList = userMapper.mapToUserViewList(users);
        return filterList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserView getUserById(Long id) {
        User user = userDao.getUserById(id);
        Document document;
        DocType docType;
        Country country;

        try {
            document = user.getDocument();
            docType = document.getDocType();
            country = document.getCountry();
        } catch (Exception e) {
            throw new ServiceException("Документ " + id + " не найден");
        }

        if (user == null) {
            throw new ServiceException("Пользователь " + id + " не найден");
        }

        UserView userView = userMapper.mapUserToUserView(user, document, docType, country);
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
        User user;
        try {
            user = userDao.getUserById(id);
        } catch (Exception e) {
            throw new ServiceException("Пользователь " + id + " не найден");
        }
        if (user == null) {
            throw new ServiceException("Пользователь " + id + " не найден");
        }
        DocType docType = null;
        String docName = userView.docName;
        if (docName != null) {
            try {
                docType = docTypeDao.getDocTypeByName(docName);
            } catch (Exception e) {
                throw new ServiceException("Тип документа с таким docName не найден");
            }
        }

        Country country = null;
        String citizenshipCode = userView.citizenshipCode;
        if (citizenshipCode != null) {
            try {
                country = countryDao.getCountryByCode(citizenshipCode);
            } catch (Exception e) {
                throw new ServiceException("Страна с таким citizenshipCode не найдена");
            }
        }

        Document document = user.getDocument();
        if (document == null) {
            throw new ServiceException("Документ " + id + " не найден");
        }

        User userToUpdate = userMapper.mapUserViewToUserUpdate(userView, user);
        Document documentToUpdate = userMapper.mapUserViewToDocUpdate(userView, document);

        userToUpdate.setDocument(documentToUpdate);
        if (docType != null) {
            documentToUpdate.setDocType(docType);
        }
        if (country != null) {
            documentToUpdate.setCountry(country);
        }
        userDao.updateUser(userToUpdate);
        documentDao.updateDocument(documentToUpdate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveUser(UserView userView) {
        if (userView.firstName == null) {
            throw new ServiceException("Не введен обязательный параметр firstName");
        }
        if (userView.position == null) {
            throw new ServiceException("Не введен обязательный параметр position");
        }

        User userToSave = userMapper.mapUserViewToUserSave(userView);

        DocType docType = null;
        String docName = userView.docName;
        if (docName != null) {
            try {
                docType = docTypeDao.getDocTypeByName(docName);
            } catch (Exception e) {
                throw new ServiceException("Тип документа с таким docName не найден");
            }
        }

        Country country = null;
        String citizenshipCode = userView.citizenshipCode;
        if (citizenshipCode != null) {
            try {
                country = countryDao.getCountryByCode(citizenshipCode);
            } catch (Exception e) {
                throw new ServiceException("Страна с таким citizenshipCode не найдена");
            }
        }

        String docNumber = userView.docNumber;
        String docDate = userView.docDate;
        Document documentToSave = new Document();
        if (docNumber != null || docDate != null) {
            documentToSave = userMapper.mapUserViewToDocSave(userView);
        }

        if (country != null) {
            documentToSave.setCountry(country);
        }
        if (docType != null) {
            documentToSave.setDocType(docType);
        }

        userToSave.setDocument(documentToSave);

        documentDao.saveDocument(documentToSave);
        userDao.saveUser(userToSave);
    }
}
