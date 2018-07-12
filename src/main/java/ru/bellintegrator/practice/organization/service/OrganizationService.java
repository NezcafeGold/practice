package ru.bellintegrator.practice.organization.service;


import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

public interface OrganizationService {
    
    Organization filterOrganization(OrganizationView organization);

    Organization getOrganizationById(int id);

    void updateOrganization(OrganizationView organization);

    void saveOrganization(OrganizationView organization);
}
