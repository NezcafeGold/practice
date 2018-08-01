package ru.bellintegrator.practice.dictionary.doctype.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class DocTypeDaoImpl implements DocTypeDao {

    @Autowired
    private final EntityManager em;

    public DocTypeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocType getDocTypeByName(String docName) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteria = builder.createQuery(DocType.class);
        Root<DocType> docTypeRoot = criteria.from(DocType.class);
        criteria.where(builder.equal(docTypeRoot.get("name"), docName));
        DocType docType = em.createQuery(criteria).getSingleResult();
        return docType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocType> getAllDocTypes() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteria = criteriaBuilder.createQuery(DocType.class);
        Root<DocType> docTypeRoot = criteria.from(DocType.class);
        criteria.select(docTypeRoot);
        List<DocType> docTypeList = em.createQuery(criteria).getResultList();
        return docTypeList;
    }
}
