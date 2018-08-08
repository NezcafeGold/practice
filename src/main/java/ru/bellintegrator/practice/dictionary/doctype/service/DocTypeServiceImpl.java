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
    private DocTypeMapper docTypeMapper;

    public DocTypeServiceImpl(DocTypeDao docTypeDao) {
        this.docTypeDao = docTypeDao;
        docTypeMapper = new DocTypeMapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<DocTypeView> getDocTypes() {
        List<DocType> docTypeList = docTypeDao.getAllDocTypes();
        List<DocTypeView> docTypeViewList = docTypeMapper.mapToDocTypeView(docTypeList);
        return docTypeViewList;
    }
}
