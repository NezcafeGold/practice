package ru.bellintegrator.practice.user.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;
import ru.bellintegrator.practice.user.model.Document;

/**
 * {@inheritDoc}
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveDocument(Document document) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Document getDocumentById(Long id) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocType getDocTypeById(Long id) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country getCountryById(Long id) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDocument(Document document) {

    }
}
