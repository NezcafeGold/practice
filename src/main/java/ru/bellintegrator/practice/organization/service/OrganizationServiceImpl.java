package ru.bellintegrator.practice.organization.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization filterOrganization(OrganizationView organization) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getOrganizationById(int id) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateOrganization(OrganizationView organization) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrganization(OrganizationView organization) {

    }
}
