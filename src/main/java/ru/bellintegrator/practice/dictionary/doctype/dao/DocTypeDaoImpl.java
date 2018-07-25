package ru.bellintegrator.practice.dictionary.doctype.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class DocTypeDaoImpl implements DocTypeDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveDocType(DocType docType) {

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
    public List<DocType> getAllDocTypes() {
        return null;
    }

}
