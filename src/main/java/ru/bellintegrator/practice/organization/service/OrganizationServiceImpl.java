package ru.bellintegrator.practice.organization.service;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationFilterView;
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
    @Transactional
    public List<OrganizationFilterView> filterOrganization(OrganizationView organizationView) {
        List<Organization> organizations = orgDao.list(organizationView);
        List<OrganizationFilterView> orgOutList = new ArrayList<>();
        for (int i = 0; i < organizations.size(); i++) {
            OrganizationFilterView filterView = new OrganizationFilterView();
            filterView.id = organizations.get(i).getId();
            filterView.name = organizations.get(i).getName();
            filterView.isActive = organizations.get(i).getActive();
            orgOutList.add(filterView);
        }
        return orgOutList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrganizationView getOrganizationById(Long id) {
        Organization organization = orgDao.getOrganizationById(id);
        if (organization == null) {
            throw new ServiceException("Организация с id" + id + "не найдена");
        }
        mapperFactory = new DefaultMapperFactory.Builder().build();
        BoundMapperFacade boundMapper = mapperFactory.getMapperFacade(Organization.class, OrganizationView.class);
        OrganizationView orgView = (OrganizationView) boundMapper.map(organization);
        return orgView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateOrganization(OrganizationView organizationView) {
        Long id = organizationView.id;
        Organization organization = orgDao.getOrganizationById(id);
        organization.setName(organizationView.name);
        organization.setFullName(organizationView.fullName);
        organization.setInn(organizationView.inn);
        organization.setKpp(organizationView.kpp);
        organization.setAddress(organizationView.address);
        organization.setPhone(organizationView.phone);
        organization.setActive(organizationView.isActive);
        orgDao.update(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveOrganization(OrganizationView organizationView) {
        Organization organization = new Organization(
                organizationView.name,
                organizationView.fullName,
                organizationView.inn,
                organizationView.kpp,
                organizationView.address,
                organizationView.phone,
                organizationView.isActive);
        orgDao.save(organization);
    }
}
