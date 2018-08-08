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
    private OrganizationMapper organizationMapper;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao orgDao) {
        this.orgDao = orgDao;
        organizationMapper = new OrganizationMapper();
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
        List<OrganizationView> orgOutList = organizationMapper.mapToOrgViewList(organizations);
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
        OrganizationView orgView = organizationMapper.mapOrgToOrgView(organization);
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
        Organization organizationToUpdate = organizationMapper.mapOrgViewToOrgUpdate(organizationView, organization);
        orgDao.update(organizationToUpdate);
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

        Organization organizationToSave = organizationMapper.mapOrgViewToOrgSave(organizationView);
        orgDao.save(organizationToSave);
    }
}
