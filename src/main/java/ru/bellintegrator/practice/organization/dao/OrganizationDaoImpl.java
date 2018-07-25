package ru.bellintegrator.practice.organization.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationFilterView;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao  {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> list(OrganizationView organizationView) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getOrganizationById(Long id) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {

    }
}
