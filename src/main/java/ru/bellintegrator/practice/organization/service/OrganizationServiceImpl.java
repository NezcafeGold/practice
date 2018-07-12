package ru.bellintegrator.practice.organization.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;


@Service
public class OrganizationServiceImpl implements OrganizationService{

    @Override
    public String filterOrganization(OrganizationView organization) {
        return null;
    }

    @Override
    public Organization getOrganizationById(int id) {
        return null;
    }

    @Override
    public void updateOrganization(OrganizationView organization) {

    }

    @Override
    public void saveOrganization(OrganizationView organization) {

    }
}
