package ru.bellintegrator.practice.office.dao;

import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;

/**
 * Дао
 */
public interface OfficeDao {

    /**
     * Отфильтровать офисы
     *
     * @param officeView
     */
    List<Office> list(OfficeView officeView);

    /**
     * Найти офис по id
     *
     * @param id
     */
    Office getOfficeById(Long id);

    /**
     * Найти офис по имени
     *
     * @param name
     */
    Office getOfficeByName(String name);


    /**
     * Обновить данные офиса
     *
     * @param office
     */
    void update(Office office);

    /**
     * Сохранить данные офиса
     *
     * @param office
     */
    void save(Office office);
}
