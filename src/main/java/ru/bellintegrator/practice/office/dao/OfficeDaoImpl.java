package ru.bellintegrator.practice.office.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    @Autowired
    private final EntityManager em;

    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> list(OfficeView officeView) {
        List<Office> officeList;
        Long orgId = officeView.orgId;
        String officeName = officeView.name;
        String officePhone = officeView.phone;
        Boolean officeActive = officeView.isActive;

        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        predicates.add(criteriaBuilder.equal(officeRoot.get("organization"), orgId));
        if (officeName != null) {
            predicates.add(criteriaBuilder.equal(officeRoot.get("name"), officeName));
        }
        if (officePhone != null) {
            predicates.add(criteriaBuilder.equal(officeRoot.get("phone"), officePhone));
        }
        if (officeActive != null) {
            predicates.add(criteriaBuilder.equal(officeRoot.get("isActive"), officeActive));
        }
        criteriaQuery.select(officeRoot).where(predicates.toArray(new Predicate[]{}));
        officeList = em.createQuery(criteriaQuery).getResultList();
        return officeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office getOfficeById(Long id) {
        return em.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office getOfficeByName(String name) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = criteria.from(Office.class);
        criteria.where(criteriaBuilder.equal(officeRoot.get("name"), name));
        Office office = em.createQuery(criteria).getSingleResult();
        return office;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) {
        em.merge(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        em.persist(office);
    }
}
