package ru.bellintegrator.practice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;
import ru.bellintegrator.practice.user.model.Document;

import javax.persistence.EntityManager;

/**
 * {@inheritDoc}
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {

    @Autowired
    private final EntityManager em;

    public DocumentDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveDocument(Document document) {
     em.persist(document);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDocument(Document document) {
        em.merge(document);
    }
}
