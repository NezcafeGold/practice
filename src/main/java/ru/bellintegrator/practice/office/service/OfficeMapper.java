package ru.bellintegrator.practice.office.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.ArrayList;
import java.util.List;

@Component
public class OfficeMapper {

    private MapperFactory mapperFactory;
    private List<OfficeView> officeFilterViews;

    public OfficeMapper() {
        mapperFactory = new DefaultMapperFactory.Builder().build();
        officeFilterViews = new ArrayList<>();
    }

    public List<OfficeView> mapToOfficeViewList(List<Office> offices){
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

    public OfficeView mapToOfficeView(Office office) {
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

    public Office mapOfficeViewToOfficeUpdate(OfficeView officeView, Office office) {
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
        return office;
    }

    public Office mapOfficeViewToOfficeSave(OfficeView officeView) {
        Office office = new Office();
        mapperFactory.classMap(OfficeView.class, Office.class)
                .field("name", "name")
                .field("address", "address")
                .field("phone", "phone")
                .field("isActive", "isActive")
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        mapper.map(officeView, office);
        return office;
    }
}
