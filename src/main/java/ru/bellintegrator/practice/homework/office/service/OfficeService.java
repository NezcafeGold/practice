package ru.bellintegrator.practice.homework.office.service;


import ru.bellintegrator.practice.homework.office.model.Office;
import ru.bellintegrator.practice.homework.office.view.OfficeView;

public interface OfficeService {

    String add(int orgId, OfficeView office);

    Office getOfficeById(int id);

    String updateOffice(OfficeView office);

    String saveOffice(OfficeView office);
}
