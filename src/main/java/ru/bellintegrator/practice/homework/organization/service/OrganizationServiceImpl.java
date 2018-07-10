package ru.bellintegrator.practice.homework.organization.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.homework.organization.model.Organization;
import ru.bellintegrator.practice.homework.organization.view.OrganizationView;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    @Override
    public List<Organization> add(OrganizationView organization) {
        return null;
    }

    @Override
    public Organization getOrganizationById(int id) {
        return null;
    }

    @Override
    public String updateOrganization(OrganizationView organization) {
        return null;
    }

    @Override
    public String saveOrganization(OrganizationView organization) {
        return null;
    }
}
