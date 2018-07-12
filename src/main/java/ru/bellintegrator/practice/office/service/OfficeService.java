package ru.bellintegrator.practice.office.service;


import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

/**
 * Сервис
 */
public interface OfficeService {

    /**
     * Отфильтровать офисы
     *
     * @param office
     */
    Office filterOffice(OfficeView office);

    /**
     * Получить офис по id
     *
     * @param id
     */
    Office getOfficeById(int id);

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
