package ru.bellintegrator.practice.office.service;


import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;

/**
 * Сервис
 */
public interface OfficeService {

    /**
     * Отфильтровать офисы
     *
     * @param office
     */
    List<OfficeView> filterOffice(OfficeView office);

    /**
     * Получить офис по id
     *
     * @param id
     */
    OfficeView getOfficeById(Long id);

    /**
     * Обновить офис
     *
     * @param office
     */
    void updateOffice(OfficeView office);

    /**
     * Сохранить офис
     *
     * @param office
     */
    void saveOffice(OfficeView office);

}
