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
    @Transactional(readOnly = true)
    public List<OfficeView> filterOffice(OfficeView officeView) {
        if (officeView.orgId == null) {
            throw new ServiceException("Не введен обязательный параметр orgId");
        }
        List<Office> offices = officeDao.list(officeView);
        List<OfficeView> officeFilterViews = new ArrayList<>();

        if (offices == null) {
            throw new ServiceException("Офисы не найдены");
        }

        mapperFactory = new DefaultMapperFactory.Builder().build();
        for (int i = 0; i < offices.size(); i++) {
            mapperFactory.classMap(Office.class, OfficeView.class)
                    .field("id", "id")
                    .field("name", "name")
                    .field("isActive", "isActive")
                    .register();
            MapperFacade mapper = mapperFactory.getMapperFacade();
            OfficeView officeFilter = mapper.map(offices.get(i), OfficeView.class);
            officeFilterViews.add(officeFilter);
        }
        return officeFilterViews;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeView getOfficeById(Long id) {
        mapperFactory = new DefaultMapperFactory.Builder().build();

        Office office = officeDao.getOfficeById(id);
        if (office == null) {
            throw new ServiceException("Офис " + id + " не найден");
        }
        mapperFactory.classMap(Office.class, OfficeView.class)
                .field("id", "id")
                .field("name", "name")
                .field("address", "address")
                .field("phone", "phone")
                .field("isActive", "isActive")
                .byDefault()
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        OfficeView officeView = mapper.map(office, OfficeView.class);
        return officeView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateOffice(OfficeView officeView) {
        mapperFactory = new DefaultMapperFactory.Builder().build();

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
        if (officeView.isActive == null) {
            throw new ServiceException("Не введен обязательный параметр isActive");
        }
        Office office = officeDao.getOfficeById(id);
        if (office == null) {
            throw new ServiceException("Офис " + id + " не найден");
        }

        mapperFactory.classMap(OfficeView.class, Office.class)
                .field("id", "id")
                .field("name", "name")
                .field("address", "address")
                .mapNulls(false)
                .field("phone", "phone")
                .field("isActive", "isActive")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(officeView, office);

        officeDao.update(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveOffice(OfficeView officeView) {
        mapperFactory = new DefaultMapperFactory.Builder().build();

        Office office = new Office();
        mapperFactory.classMap(OfficeView.class, Office.class)
                .field("name", "name")
                .field("address", "address")
                .field("phone", "phone")
                .field("isActive", "isActive")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(officeView, office);

        officeDao.save(office);
    }


}
