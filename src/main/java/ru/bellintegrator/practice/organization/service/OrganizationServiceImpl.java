package ru.bellintegrator.practice.organization.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao orgDao;
    private MapperFactory mapperFactory;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao orgDao) {
        this.orgDao = orgDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> filterOrganization(OrganizationView organizationView) {
        if (organizationView.name == null) {
            throw new ServiceException("Не введен обязательный параметр name");
        }
        List<Organization> organizations = orgDao.list(organizationView);
        List<OrganizationView> orgOutList = new ArrayList<>();
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
            orgOutList.add(filterView);
        }
        return orgOutList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationView getOrganizationById(Long id) {
        Organization organization = orgDao.getOrganizationById(id);
        if (organization == null) {
            throw new ServiceException("Организация с id " + id + " не найдена");
        }
        OrganizationView orgView = new OrganizationView();
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
        mapper.map(organization, orgView);
        return orgView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateOrganization(OrganizationView organizationView) {

        if (organizationView.id == null) {
            throw new ServiceException("Не введен обязательный параметр id");
        }
        if (organizationView.name == null) {
            throw new ServiceException("Не введен обязательный параметр name");
        }
        if (organizationView.fullName == null) {
            throw new ServiceException("Не введен обязательный параметр fullName");
        }
        if (organizationView.inn == null) {
            throw new ServiceException("Не введен обязательный параметр inn");
        }
        if (organizationView.kpp == null) {
            throw new ServiceException("Не введен обязательный параметр kpp");
        }
        if (organizationView.address == null) {
            throw new ServiceException("Не введен обязательный параметр address");
        }

        Long id = organizationView.id;
        Organization organization = orgDao.getOrganizationById(id);
        if (organization == null) {
            throw new ServiceException("Организация " + id + " не найдена");
        }

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

        orgDao.update(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveOrganization(OrganizationView organizationView) {

        if (organizationView.name == null) {
            throw new ServiceException("Не введен обязательный параметр name");
        }
        if (organizationView.fullName == null) {
            throw new ServiceException("Не введен обязательный параметр fullName");
        }
        if (organizationView.inn == null) {
            throw new ServiceException("Не введен обязательный параметр inn");
        }
        if (organizationView.kpp == null) {
            throw new ServiceException("Не введен обязательный параметр kpp");
        }
        if (organizationView.address == null) {
            throw new ServiceException("Не введен обязательный параметр address");
        }

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

        orgDao.save(organization);
    }
}
