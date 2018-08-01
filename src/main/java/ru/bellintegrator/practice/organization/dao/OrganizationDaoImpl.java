package ru.bellintegrator.practice.organization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import javax.persistence.EntityManager;
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
public class OrganizationDaoImpl implements OrganizationDao {

    @Autowired
    private final EntityManager em;

    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> list(OrganizationView organizationView) {
        List<Organization> organizationList;
        String orgName = organizationView.name;
        String orgInn = organizationView.inn;
        Boolean orgActive = organizationView.isActive;

        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        predicates.add(criteriaBuilder.equal(organizationRoot.get("name"), orgName));
        if (orgInn != null) {
            predicates.add(criteriaBuilder.equal(organizationRoot.get("inn"), orgInn));
        }
        if (orgActive != null) {
            predicates.add(criteriaBuilder.equal(organizationRoot.get("isActive"), orgActive));
        }
        criteriaQuery.select(organizationRoot).where(predicates.toArray(new Predicate[]{}));
        organizationList = em.createQuery(criteriaQuery).getResultList();
        return organizationList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getOrganizationById(Long id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization) {
        em.merge(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getOrganizationByName(String name) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteria.from(Organization.class);
        criteria.where(criteriaBuilder.equal(organizationRoot.get("name"), name));
        Organization organization = em.createQuery(criteria).getSingleResult();
        return organization;
    }
}
