package ru.bellintegrator.practice.organization.service;


import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

/**
 * Сервис
 */
public interface OrganizationService {

    /**
     * Отфильтровать организации
     *
     * @param organization
     */
    Organization filterOrganization(OrganizationView organization);

    /**
     * Получить организацию по id
     *
     * @param id
     */
    Organization getOrganizationById(int id);

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
