package ru.bellintegrator.practice.office.service;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeFilterView;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private MapperFactory mapperFactory;

    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<OfficeFilterView> filterOffice(OfficeView officeView) {
        List<Office> offices = officeDao.list(officeView);
        List<OfficeFilterView> officeFilterViews = new ArrayList<>();

        if (offices == null) {
            throw new ServiceException("Офисы не найдены");
        }

        for (int i = 0; i < offices.size(); i++) {
            OfficeFilterView officeFilter = new OfficeFilterView();
            officeFilter.id = offices.get(i).getId();
            officeFilter.name = offices.get(i).getName();
            officeFilter.isActive = offices.get(i).getActive();
            officeFilterViews.add(officeFilter);
        }
        return officeFilterViews;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeView getOfficeById(Long id) {
        Office office = officeDao.getOfficeById(id);
        if(office == null) {
            throw new ServiceException("Офис " + id + " не найден");
        }
        mapperFactory = new DefaultMapperFactory.Builder().build();
        BoundMapperFacade boundMapper = mapperFactory.getMapperFacade(Office.class, OfficeView.class);
        OfficeView officeView = (OfficeView) boundMapper.map(office);
        return officeView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateOffice(OfficeView officeView) {
        Long id = officeView.id;
        Office office = officeDao.getOfficeById(id);

        if(office == null) {
            throw new ServiceException("Офис " + id + " не найден");
        }

        office.setName(officeView.name);
        office.setAddress(officeView.address);
        office.setPhone(officeView.phone);
        office.setActive(officeView.isActive);
        officeDao.update(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveOffice(OfficeView officeView) {
        Office office = new Office(officeView.name,
                officeView.address,
                officeView.address,
                officeView.isActive);
        officeDao.save(office);
    }


}
