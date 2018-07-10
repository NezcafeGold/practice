package ru.bellintegrator.practice.homework.organization.service;


import ru.bellintegrator.practice.homework.organization.model.Organization;
import ru.bellintegrator.practice.homework.organization.view.OrganizationView;

import java.util.List;

public interface OrganizationService {
    
    List<Organization> add(OrganizationView organization);

    Organization getOrganizationById(int id);

    String updateOrganization(OrganizationView organization);

    String saveOrganization(OrganizationView organization);
}
