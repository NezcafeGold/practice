package ru.bellintegrator.practice.office.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private final OfficeDao officeDao;
    private OfficeMapper officeMapper;

    public OfficeServiceImpl(OfficeDao officeDao) {
        this.officeDao = officeDao;
        officeMapper = new OfficeMapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeView> filterOffice(OfficeView officeView) {
        if (officeView.orgId == null) {
            throw new ServiceException("Не введен обязательный параметр orgId");
        }
        List<Office> offices = officeDao.list(officeView);
        if (offices == null) {
            throw new ServiceException("Офисы не найдены");
        }
        List<OfficeView> officeViewList = officeMapper.mapToOfficeViewList(offices);
        return officeViewList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeView getOfficeById(Long id) {
        Office office = officeDao.getOfficeById(id);
        if (office == null) {
            throw new ServiceException("Офис " + id + " не найден");
        }
        OfficeView officeView = officeMapper.mapToOfficeView(office);
        return officeView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateOffice(OfficeView officeView) {
        Long id = officeView.id;
        if (id == null) {
            throw new ServiceException("Не введен обязательный параметр id");
        }
        if (officeView.name == null) {
            throw new ServiceException("Не введен обязательный параметр name");
        }
        if (officeView.address == null) {
            throw new ServiceException("Не введен обязательный параметр address");
        }
        Office office = officeDao.getOfficeById(id);
        if (office == null) {
            throw new ServiceException("Офис " + id + " не найден");
        }
        Office officeToUpdate = officeMapper.mapOfficeViewToOfficeUpdate(officeView, office);
        officeDao.update(officeToUpdate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveOffice(OfficeView officeView) {
        Office office = officeMapper.mapOfficeViewToOfficeSave(officeView);
        officeDao.save(office);
    }
}
