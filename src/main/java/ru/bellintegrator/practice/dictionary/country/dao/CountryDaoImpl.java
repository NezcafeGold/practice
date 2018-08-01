package ru.bellintegrator.practice.dictionary.country.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionary.country.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class CountryDaoImpl implements CountryDao {

    @Autowired
    private final EntityManager em;

    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country getCountryByCode(String citizenshipCode) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteria = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteria.from(Country.class);
        criteria.where(criteriaBuilder.equal(countryRoot.get("code"), citizenshipCode));
        Country country = em.createQuery(criteria).getSingleResult();
        return country;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getAllCountries() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteria = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteria.from(Country.class);
        criteria.select(countryRoot);
        List<Country> countryList = em.createQuery(criteria).getResultList();
        return countryList;
    }
}
