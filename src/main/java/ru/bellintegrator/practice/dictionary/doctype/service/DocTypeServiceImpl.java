package ru.bellintegrator.practice.dictionary.doctype.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dictionary.doctype.dao.DocTypeDao;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;
import ru.bellintegrator.practice.dictionary.doctype.view.DocTypeView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class DocTypeServiceImpl implements DocTypeService {

    @Autowired
    private final DocTypeDao docTypeDao;
    private MapperFactory mapperFactory;

    public DocTypeServiceImpl(DocTypeDao docTypeDao) {
        this.docTypeDao = docTypeDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<DocTypeView> getDocTypes() {
        List<DocTypeView> docTypeViewList = new ArrayList<>();
        List<DocType> docTypeList = docTypeDao.getAllDocTypes();

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
