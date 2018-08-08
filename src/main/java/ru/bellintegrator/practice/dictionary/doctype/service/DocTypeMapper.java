package ru.bellintegrator.practice.dictionary.doctype.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;
import ru.bellintegrator.practice.dictionary.doctype.view.DocTypeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Маппер для DocType
 */
public class DocTypeMapper {

    private MapperFactory mapperFactory;
    private List<DocTypeView> docTypeViewList;

    /**
     * Конструктор маппера для DocType
     */
    public DocTypeMapper() {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        docTypeViewList = new ArrayList<>();
    }

    /**
     * Маппер, который принимает список сущностей типов документво и превращает в список представлений типов документов
     */
    public List<DocTypeView> mapToDocTypeView(List<DocType> docTypeList) {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        for (int i = 0; i < docTypeList.size(); i++) {
            mapperFactory.classMap(DocType.class, DocTypeView.class)
                    .field("name", "name")
                    .field("code", "code")
                    .byDefault()
                    .register();
            MapperFacade mapper = mapperFactory.getMapperFacade();
            DocTypeView docTypeView = mapper.map(docTypeList.get(i), DocTypeView.class);
            docTypeViewList.add(docTypeView);
        }
        return docTypeViewList;
    }
}
