package ru.bellintegrator.practice.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;
import ru.bellintegrator.practice.user.model.Document;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private final EntityManager em;

    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> filterUser(UserView userView) {
        List<User> userList;
        Long officeId = userView.officeId;
        String firstName = userView.firstName;
        String secondName = userView.secondName;
        String middleName = userView.middleName;
        String position = userView.position;
        String docCode = userView.docCode;
        String citizenshipCode = userView.citizenshipCode;

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> userQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = userQuery.from(User.class);
        Join<User, Document> docJoin = userRoot.join("document");
        Join<Document, DocType> docTypeJoin = docJoin.join("docType");
        Join<Document, Country> countryJoin = docJoin.join("country");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(userRoot.get("office"), officeId));

        if (firstName != null) {
            predicates.add(criteriaBuilder.equal(userRoot.get("firstName"), firstName));
        }
        if (secondName != null) {
            predicates.add(criteriaBuilder.equal(userRoot.get("secondName"), secondName));
        }
        if (middleName != null) {
            predicates.add(criteriaBuilder.equal(userRoot.get("middleName"), middleName));
        }
        if (position != null) {
            predicates.add(criteriaBuilder.equal(userRoot.get("position"), position));
        }

        if (docCode != null) {
            predicates.add(criteriaBuilder.equal(docTypeJoin.get("code"), docCode));
        }

        if (citizenshipCode != null) {
            predicates.add(criteriaBuilder.equal(countryJoin.get("code"), citizenshipCode));
        }
        userQuery.select(userRoot).where(predicates.toArray(new Predicate[]{}));
        userList = em.createQuery(userQuery).getResultList();
        return userList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserByName(String name) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        criteria.where(criteriaBuilder.equal(userRoot.get("firstName"), name));
        User user = em.createQuery(criteria).getSingleResult();
        return user;
    }
}
