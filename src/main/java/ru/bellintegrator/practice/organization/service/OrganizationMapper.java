package ru.bellintegrator.practice.organization.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.ArrayList;
import java.util.List;

public class OrganizationMapper {

    private MapperFactory mapperFactory;


    public List<OrganizationView> mapToOrgViewList(List<Organization> organizations) {
        List<OrganizationView> organizationViewsList = new ArrayList<>();
        for (int i = 0; i < organizations.size(); i++) {
            OrganizationView filterView = new OrganizationView();
            mapperFactory = new DefaultMapperFactory.Builder().build();
            mapperFactory.classMap(Organization.class, OrganizationView.class)
                    .field("id", "id")
                    .field("name", "name")
                    .field("isActive", "isActive")
                    .register();
            MapperFacade mapper = mapperFactory.getMapperFacade();
            mapper.map(organizations.get(i), filterView);
            if (filterView.id == null) {
                filterView.id = organizations.get(i).getId();
            }
            organizationViewsList.add(filterView);
        }
        return organizationViewsList;
    }

    public OrganizationView mapOrgToOrgView(Organization organization) {
        OrganizationView organizationView = new OrganizationView();
        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Organization.class, OrganizationView.class)
                .mapNulls(false)
                .field("id", "id")
                .field("name", "name")
                .field("fullName", "fullName")
                .field("inn", "inn")
                .field("kpp", "kpp")
                .field("address", "address")
                .field("phone", "phone")
                .field("isActive", "isActive")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(organization, organizationView);
        return organizationView;
    }

    public Organization mapOrgViewToOrgUpdate(OrganizationView organizationView, Organization organization) {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(OrganizationView.class, Organization.class)
                .field("name", "name")
                .field("fullName", "fullName")
                .field("inn", "inn")
                .field("kpp", "kpp")
                .field("address", "address")
                .mapNulls(false)
                .field("phone", "phone")
                .field("isActive", "isActive")
                .byDefault()
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(organizationView, organization);
        return organization;
    }

    public Organization mapOrgViewToOrgSave(OrganizationView organizationView) {
        Organization organization = new Organization();
        mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(OrganizationView.class, Organization.class)
                .field("name", "name")
                .field("fullName", "fullName")
                .field("inn", "inn")
                .field("kpp", "kpp")
                .field("address", "address")
                .mapNulls(false)
                .field("phone", "phone")
                .field("isActive", "isActive")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(organizationView, organization);
        return organization;
    }
}
