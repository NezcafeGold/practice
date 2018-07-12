package ru.bellintegrator.practice.office.service;


import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

public interface OfficeService {

    String filterOffice(int orgId, OfficeView office);

    Office getOfficeById(int id);

    void updateOffice(OfficeView office);

    void saveOffice(OfficeView office);

}
