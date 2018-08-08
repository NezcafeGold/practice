package ru.bellintegrator.practice.user.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;
import ru.bellintegrator.practice.user.model.Document;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    private MapperFactory mapperFactory;

    public UserMapper() {
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }


    public List<UserView> mapToUserViewList(List<User> users) {
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

    public UserView mapUserToUserView(User user, Document document, DocType docType, Country country) {
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

    public User mapUserViewToUserUpdate(UserView userView, User user) {
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
        return user;
    }

    public Document mapUserViewToDocUpdate(UserView userView, Document document) {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(UserView.class, Document.class)
                .mapNulls(false)
                .field("docNumber", "docNumber")
                .field("docDate", "docDate")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(userView, document);
        return document;
    }

    public User mapUserViewToUserSave(UserView userView) {
        User user = new User();
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
        return user;
    }

    public Document mapUserViewToDocSave(UserView userView) {
        Document document = new Document();
        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(UserView.class, Document.class)
                .field("docNumber", "docNumber")
                .field("docDate", "docDate")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(userView, document);
        return document;
    }
}
