package ru.bellintegrator.practice.organization.service;


import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

/**
 * Сервис
 */
public interface OrganizationService {

    /**
     * Отфильтровать организации
     *
     * @param organization
     */
    List<OrganizationView> filterOrganization(OrganizationView organization);

    /**
     * Получить организацию по id
     *
     * @param id
     */
    OrganizationView getOrganizationById(Long id);

    /**
     * Обновить организацию
     *
     * @param organization
     */
    void updateOrganization(OrganizationView organization);

    /**
     * Сохранить организацию
     *
     * @param organization
     */
    void saveOrganization(OrganizationView organization);

}
