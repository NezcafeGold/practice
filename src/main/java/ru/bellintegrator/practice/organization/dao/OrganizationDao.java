package ru.bellintegrator.practice.organization.dao;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

/**
 * Дао
 */
public interface OrganizationDao {

    /**
     * Отфильтровать пользователей
     *
     * @param organizationView
     */
    List<Organization> list(OrganizationView organizationView);

    /**
     * Найти организацию по id
     *
     * @param id
     */
    Organization getOrganizationById(Long id);

    /**
     * Обновить данные организации
     *
     * @param organization
     */
    void update(Organization organization);

    /**
     * Сохранить данные организации
     *
     * @param organization
     */
    void save(Organization organization);
}
